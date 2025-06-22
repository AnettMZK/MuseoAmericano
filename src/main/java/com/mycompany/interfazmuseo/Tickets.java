package com.mycompany.interfazmuseo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import controllers.MuEntradasJpaController;
import controllers.MuSalasJpaController;
import persistence.MuEntradas;
import persistence.MuSalas;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class Tickets {
    private static final double COMMISSION_RATE = 0.05; // 5% commission
    private static final double IVA_RATE = 0.13; // 13% IVA
    private final MuSalasJpaController salasJpa = new MuSalasJpaController();
    private final MuEntradasJpaController entradasJpa = new MuEntradasJpaController();

    // Load halls for a given museum type
    public List<Hall> loadHalls(String museumType) {
        List<Hall> halls = new ArrayList<>();
        List<MuSalas> salas = salasJpa.findMuseoEntities();
        for (MuSalas sala : salas) {
            if (sala.getTipoMuseo().equals(museumType != null ? museumType : "Historia")) {
                halls.add(new Hall(
                    sala.getIdSala(),
                    sala.getNombre(),
                    sala.getDescripcion(),
                    sala.getPrecio() != null ? sala.getPrecio() : 10.0,
                    sala.getTipoMuseo()
                ));
            }
        }
        return halls;
    }

    // Calculate ticket price, IVA, and total
    public double[] calculatePrices(List<Hall> selectedHalls) {
        double ticketPrice = selectedHalls.stream().mapToDouble(Hall::getPrice).sum();
        double iva = ticketPrice * IVA_RATE;
        double total = ticketPrice + iva;
        return new double[]{ticketPrice, iva, total};
    }

    // Save ticket to database and return ticket ID
    public int saveTicket(Ticket ticket) {
        EntityManager em = entradasJpa.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            MuEntradas entrada = new MuEntradas();
            entrada.setNombreVisitante(ticket.getVisitorName());
            entrada.setFechaVisita(java.sql.Date.valueOf(ticket.getVisitDate()));
            entrada.setPrecioTotal(ticket.getTotalPrice());
            entrada.setTipoMuseo(ticket.getMuseumType());
            entrada.setMuSalasList(new ArrayList<>());
            for (Hall hall : ticket.getHalls()) {
                MuSalas sala = em.find(MuSalas.class, hall.getId());
                if (sala != null) {
                    entrada.getMuSalasList().add(sala);
                }
            }
            entradasJpa.create(entrada);
            em.getTransaction().commit();
            return entrada.getIdEntrada();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return -1;
        } finally {
            em.close();
        }
    }

    // Register commission for a ticket
    public void registerCommission(int ticketId, double totalPrice) {
        EntityManager em = entradasJpa.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            MuComision comision = new MuComision();
            comision.setIdEntrada(ticketId);
            comision.setMontoComision(totalPrice * COMMISSION_RATE);
            comision.setFechaRegistro(new java.sql.Timestamp(System.currentTimeMillis()));
            em.persist(comision);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Generate QR code for a ticket
    public void generateQRCode(int ticketId, Ticket ticket) throws Exception {
        String qrContent = "Ticket ID: " + ticketId + "\nVisitor: " + ticket.getVisitorName() + "\nDate: " + ticket.getVisitDate() + "\nMuseum: " + ticket.getMuseumType();
        String filePath = "ticket_" + ticketId + ".jpg";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 200, 200);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "JPG", path);
    }

    // Parse ticket ID from QR code content
    public int parseTicketId(String qrContent) {
        try {
            String[] lines = qrContent.split("\n");
            for (String line : lines) {
                if (line.startsWith("Ticket ID: ")) {
                    return Integer.parseInt(line.substring("Ticket ID: ".length()).trim());
                }
            }
        } catch (NumberFormatException e) {
            // Invalid number format
        }
        return -1;
    }

    // Validate ticket and return ticket details
    public Ticket validateTicket(int ticketId) {
        EntityManager em = entradasJpa.getEmf().createEntityManager();
        try {
            MuEntradas entrada = em.find(MuEntradas.class, ticketId);
            if (entrada != null && entrada.getFechaVisita().toLocalDate().equals(LocalDate.now())) {
                List<Hall> halls = new ArrayList<>();
                for (MuSalas sala : entrada.getMuSalasList()) {
                    halls.add(new Hall(
                        sala.getIdSala(),
                        sala.getNombre(),
                        sala.getDescripcion(),
                        sala.getPrecio() != null ? sala.getPrecio() : 10.0,
                        sala.getTipoMuseo()
                    ));
                }
                return new Ticket(
                    entrada.getNombreVisitante(),
                    "",
                    entrada.getFechaVisita().toLocalDate(),
                    halls,
                    entrada.getPrecioTotal(),
                    entrada.getTipoMuseo()
                );
            }
            return null;
        } finally {
            em.close();
        }
    }
}
