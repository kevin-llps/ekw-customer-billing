# ekw-customer-billing

## Description

ekw-customer-billing est une application de gestion de la facturation des clients.
Leur facturation porte sur leur consommation en énergie (électrique, gaz).

Elle expose une API REST permettant de calculer
le montant à facturer à un client pour un mois calendaire.

Il s'agit également d'une application stateless, les données seront stockées dans une base de données
(Voir les flux entrants/sortants pour plus de détails).

## Fonctionnalités

| Endpoint REST                | Description                                                                       |
|------------------------------|-----------------------------------------------------------------------------------|
| POST /customers/{id}/billing | Calcule et renvoie le montant à facturer à un client pour un mois calendaire.     |
|                              | - Lorsque l'id du customer n'est pas répertoiriée, un code HTTP 404 est retourné. |

## Tester l'application en local

1) Lancer le build du container Docker (hébergeant la base de données PostgreSQL).

`docker compose up`

2) Lancer le build Maven.
   Remarque: Le pom.xml est configuré pour Java 21 et Spring Boot 3.

`mvn clean install`

3) Créer automatiquement les tables à partir du schéma Liquibase :

`mvn liquibase:update`

4) Démarrer l'application avec `EkwCustomerBillingApplication`.

5) Des fichiers Postman sont mis à disposition pour tester l'application :

- `src/main/resources/postman/collection`
- `src/main/resources/postman/env`

## Flux entrant(s)

| Application              | Protocole | Port | Description                                      |
|--------------------------|-----------|------|--------------------------------------------------|
| Postman (tests en local) | HTTP      | 80   | Appel de l'API REST de gestion de la facturation |

## Flux sortant(s)

| Application | Protocole | Port | Description                        |
|-------------|-----------|------|------------------------------------|
| PostgreSQL  | TCP       | 5432 | Données clientes et de facturation |

## Choix d'implémentation

### Architecture en couches

| Présentation              | Métier                 | Accès aux données |
|---------------------------|------------------------|-------------------|
| API REST (Spring Web MVC) | - Entités JPA          | Spring Data JPA   |
|                           | - Classe(s) de service |                   |

## Base de données (PostgreSQL)

### Table `company_customer` (Client Pro)

| Colonne       | Type         | Description                              |
|---------------|--------------|------------------------------------------|
| reference_id  | CHAR(12)     | Id de référence du client (Clé primaire) |
| siret         | CHAR(14)     | Numéro de siret                          |
| name          | VARCHAR(200) | Raison sociale du client                 |
| sales_revenue | NUMERIC      | Chiffre d'affaires                       |

### Table `individual_customer` (Client Particulier)

| Colonne      | Type         | Description                                    |
|--------------|--------------|------------------------------------------------|
| reference_id | CHAR(12)     | Id de référence du client (Clé primaire)       |
| title_id     | INTEGER      | Id correspondant à la civilité (Clé étrangère) |
| lastname     | VARCHAR(200) | Raison sociale du client                       |
| firstname    | VARCHAR(200) | Chiffre d'affaires                             |

### Table `title` (Civilité)

| Colonne     | Type         | Description                                        |
|-------------|--------------|----------------------------------------------------|
| id          | INTEGER      | Id de la civilité (Clé primaire)                   |
| designation | VARCHAR(200) | Désignation de la civilité (Monsieur, Madame, ...) |

### Table `energy_type` (Type d'énergie)

| Colonne     | Type         | Description                                      |
|-------------|--------------|--------------------------------------------------|
| id          | INTEGER      | Id du type d'énergie (Clé primaire)              |
| designation | VARCHAR(200) | Désignation du type d'énergie (Electricité, Gaz) |

### Table `company_price`

| Colonne             | Type    | Description                                        |
|---------------------|---------|----------------------------------------------------|
| id                  | INTEGER | Id (Clé primaire)                                  |
| energy_type_id      | INTEGER | Id correspondant au type d'énergie (Clé étrangère) |
| sales_revenue_limit | NUMERIC | Seuil du chiffre d'affaires                        |
| min_price           | NUMERIC | Tarif minimum                                      |
| max_price           | NUMERIC | Tarif maximum                                      |

### Table `individual_price`

| Colonne        | Type    | Description                                        |
|----------------|---------|----------------------------------------------------|
| id             | INTEGER | Id (Clé primaire)                                  |
| energy_type_id | INTEGER | Id correspondant au type d'énergie (Clé étrangère) |
| price          | NUMERIC | Tarif                                              |

### Table `company_customer_energy`

| Colonne             | Type     | Description                                        |
|---------------------|----------|----------------------------------------------------|
| id                  | INTEGER  | Id (Clé primaire)                                  |
| company_customer_id | CHAR(12) | Id correspondant au client pro (Clé étrangère)     |
| energy_type_id      | INTEGER  | Id correspondant au type d'énergie (Clé étrangère) |

### Table `individual_customer_energy`

| Colonne                | Type     | Description                                            |
|------------------------|----------|--------------------------------------------------------|
| id                     | INTEGER  | Id (Clé primaire)                                      |
| individual_customer_id | CHAR(12) | Id correspondant au client particulier (Clé étrangère) |
| energy_type_id         | INTEGER  | Id correspondant au type d'énergie (Clé étrangère)     |
