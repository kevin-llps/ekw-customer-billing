package fr.kevin.llps.ekw.customer.billing.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record IndividualCustomer(String referenceId,
                                 String title,
                                 String lastname,
                                 String firstname,
                                 List<String> energyTypes) implements Customer {

    public Invoice compute(CustomerConsumptionMetrics consumptionMetrics,
                           Map<String, IndividualPrice> individualPriceByEnergyTypes) {

        Map<String, BigDecimal> consumptionByEnergyTypes = consumptionMetrics.consumptionByEnergyTypes();

        BigDecimal billingAmount = individualPriceByEnergyTypes.entrySet()
                .stream()
                .filter(entry -> energyTypes.contains(entry.getKey()))
                .map(entry -> computeAmountByEnergyType(entry.getKey(), entry.getValue(), consumptionByEnergyTypes))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Invoice(billingAmount);
    }

    private BigDecimal computeAmountByEnergyType(String energyType,
                                                 IndividualPrice individualPrice,
                                                 Map<String, BigDecimal> consumptionByEnergyTypes) {

        BigDecimal price = individualPrice.price();

        BigDecimal consumption = consumptionByEnergyTypes.getOrDefault(energyType, BigDecimal.ZERO);

        return consumption.multiply(price);
    }

}
