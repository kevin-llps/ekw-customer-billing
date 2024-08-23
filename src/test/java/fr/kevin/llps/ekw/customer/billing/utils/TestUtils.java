package fr.kevin.llps.ekw.customer.billing.utils;

import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestUtils {

    public static final String ELECTRICAL_ENERGY_TYPE = "Electricité";
    public static final String GAS_ENERGY_TYPE = "Gaz";

    public static String readResource(Resource resource) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

}
