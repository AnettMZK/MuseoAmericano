package clases;

import java.util.*;
import java.util.stream.Collectors;
import persistence.MuSalas;

public class ListSalas {

   
    public static List<MuSalas> obtenerMejoresSalas(List<MuSalas> todasLasSalas) {
        return todasLasSalas.stream()
                .sorted((s1, s2) -> s2.getPromedioValoracion().compareTo(s1.getPromedioValoracion()))
                .limit(10)
                .collect(Collectors.toList());
    }

   
    public static List<MuSalas> obtenerPeoresSalas(List<MuSalas> todasLasSalas) {
        return todasLasSalas.stream()
                .sorted(Comparator.comparing(MuSalas::getPromedioValoracion))
                .limit(10)
                .collect(Collectors.toList());
    }
}
