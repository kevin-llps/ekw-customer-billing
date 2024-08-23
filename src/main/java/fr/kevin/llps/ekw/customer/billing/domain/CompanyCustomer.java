package fr.kevin.llps.ekw.customer.billing.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record CompanyCustomer(String referenceId,
                              String siret,
                              String name,
                              BigDecimal salesRevenue,
                              List<String> energyTypes) implements Customer {

    public Invoice compute(CustomerConsumptionMetrics consumptionMetrics,
                           Map<String, CompanyPrice> companyPriceByEnergyTypes) {

        Map<String, BigDecimal> consumptionByEnergyTypes = consumptionMetrics.consumptionByEnergyTypes();

        BigDecimal billingAmount = companyPriceByEnergyTypes.entrySet()
                .stream()
                .filter(entry -> energyTypes.contains(entry.getKey()))
                .map(entry -> computeAmountByEnergyType(entry.getKey(), entry.getValue(), consumptionByEnergyTypes))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Invoice(billingAmount);
    }

    private BigDecimal computeAmountByEnergyType(String energyType,
                                                 CompanyPrice companyPrice,
                                                 Map<String, BigDecimal> consumptionByEnergyTypes) {

        BigDecimal price = getPrice(companyPrice);

        BigDecimal consumption = consumptionByEnergyTypes.getOrDefault(energyType, BigDecimal.ZERO);

        return consumption.multiply(price);
    }

    private BigDecimal getPrice(CompanyPrice companyPrice) {
        if (salesRevenue.compareTo(companyPrice.salesRevenueLimit()) > 0) {
            return companyPrice.secondPriceOffer();
        }
        return companyPrice.firstPriceOffer();
    }

}
