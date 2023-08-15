# Agricultural Cooperative Management Application

## Introduction

In the world of rural agriculture, it's common to find **family-owned farms** of varying sizes, producing a wide range of products including *olives*, *grapes*, *pistachios*, *oranges*, and *melons*. In our globalized context, the path to success in distribution and sales often involves forming collaborations among these small-scale producers. This is where **cooperatives** play a crucial role, fostering cooperation among farmers and advancing their shared interests. This document outlines the requirements, constraints, and functionalities of an application designed to streamline the management of an agricultural cooperative within this unique rural environment.

## Actors

### 1. Producers

Producers are the core contributors to the cooperative. They supply various products, categorized as follows:

- **Small Producers**: Those owning land up to or equal to a specified threshold (e.g., 5 hectares). They can contribute up to five different products.
- **Large Producers**: Those with land exceeding the small producer threshold.

Additionally, there's the concept of forming **"producer federations"** where small producers collaborate to contribute products. The total land area of a federation must not surpass the small producer limit.

Each product has a specific *yield per hectare*, indicating the quantity of output per hectare, and a *reference price per kilogram* exclusive of taxes.

### 2. Logistics

Logistics plays a crucial role in transporting products to distributors and consumers. Logistics can be classified into two main categories:

- **Perishable Products**: These require specific transport conditions. Costs vary based on distance, particularly whether it's within or beyond 100 km.
- **Non-Perishable Products**: These products require a more complex logistics setup for distances exceeding 100 km. This setup consists of multiple 50 km segments.

Transportation costs are calculated using product reference prices, distance, and the number of segments.

### 3. Distributors and Consumers

Distributors purchase products for resale, while consumers directly buy from the cooperative. Distributors pay an additional 5% over the reference price, which covers logistics costs. Consumers pay 15% above the reference price and are subject to a 10% VAT on both product and logistics expenses.

## Functionalities

The application aims to provide the following functionalities:

1. **Producer Management**:
    - Register small and large producers.
    - Manage producer federations.

2. **Production Management**:
    - Register products with associated yield per hectare and reference price.

3. **Logistics Management**:
    - Calculate transportation costs based on distance and product type.
    - Manage both perishable and non-perishable logistics.

4. **Distributor and Consumer Transactions**:
    - Calculate prices for distributors and consumers, including tax components.
    - Handle VAT calculations for product and logistics costs.

5. **Reporting and Analytics**:
    - Generate reports on producer yields, product sales, and logistics expenses.
    - Provide insights into revenue and costs.

## Conclusion

The proposed application aims to streamline the management of rural agricultural cooperatives by offering tools for producer collaboration, appropriate pricing, logistics management, and customer transactions. This document outlines the main actors, rules, requirements, and functionalities of the application.

---
