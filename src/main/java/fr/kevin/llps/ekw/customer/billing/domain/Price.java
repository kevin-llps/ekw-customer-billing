package fr.kevin.llps.ekw.customer.billing.domain;

public sealed interface Price permits CompanyPrice, IndividualPrice {
}
