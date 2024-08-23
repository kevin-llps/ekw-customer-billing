package fr.kevin.llps.ekw.customer.billing.domain;

public sealed interface Customer permits CompanyCustomer, IndividualCustomer {

}
