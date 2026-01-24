# Microservice Job Platform

## Обзор проекта

Данный проект представляет собой **микросервисную платформу для управления вакансиями, компаниями и отзывами**, построенную на базе **Spring Boot 4.x** и **Spring Cloud 2025.1.0**.

Архитектура ориентирована на реальные production‑сценарии:

* сервис‑дискавери (Eureka)
* централизованную конфигурацию
* API Gateway
* распределённую трассировку
* отказоустойчивость (Circuit Breaker)
* асинхронный обмен сообщениями
* контейнеризацию и оркестрацию

Проект разворачивается как через **Docker Compose**, так и в **Kubernetes (Minikube)**.

---

## Архитектура

```
            ┌─────────────┐
            │   Gateway   │  :8084
            └──────┬──────┘
                   │
     ┌─────────────┼─────────────┐
     │             │             │
┌──────────┐  ┌──────────┐  ┌──────────┐
│ Company  │  │   Job    │  │  Review  │
│ Service  │  │ Service  │  │ Service  │
│ :8081    │  │ :8082    │  │ :8083    │
└────┬─────┘  └────┬─────┘  └────┬─────┘
     │             │             │
     └─────────────┴─────────────┘
                   │
              ┌──────────┐
              │ Postgres │
              └──────────┘
```

Инфраструктурные сервисы:

* **Eureka Server** – сервис‑дискавери
* **Config Server** – централизованные конфигурации
* **Zipkin** – распределённая трассировка
* **RabbitMQ** – событийное взаимодействие

---

## Сервисы

### 1. Company Microservice

**Назначение:** управление компаниями

* Spring Web MVC
* Spring Data JPA
* PostgreSQL
* Eureka Client
* Actuator + Micrometer

---

### 2. Job Microservice

**Назначение:** управление вакансиями

Ключевые особенности:

* OpenFeign для синхронного взаимодействия
* Resilience4j:

    * Circuit Breaker
    * Retry
    * Rate Limiter
* Actuator Health Indicators

Используется для демонстрации устойчивости к сбоям внешних сервисов.

---

### 3. Review Microservice

**Назначение:** отзывы о компаниях

Особенности:

* Асинхронное взаимодействие через **RabbitMQ**
* Валидация входных данных
* JPA + PostgreSQL

---

### 4. Gateway Microservice

**Назначение:** единая точка входа (API Gateway)

* Spring Cloud Gateway (WebMVC)
* Интеграция с Eureka (`lb://SERVICE-NAME`)
* Проксирование:

    * `/companies/**`
    * `/jobs/**`
    * `/reviews/**`
    * `/eureka/**`

---

### 5. Service Registry (Eureka)

* Netflix Eureka Server
* Автоматическая регистрация сервисов
* Используется как для Gateway, так и для Feign‑клиентов

---

### 6. Config Server

* Spring Cloud Config Server
* Хранение конфигураций вне сервисов
* Профили: `default`, `docker`

---

## Хранилище данных

### PostgreSQL

* Один кластер БД
* Используется всеми бизнес‑сервисами
* StatefulSet в Kubernetes
* Volume для персистентности данных

Особенности:

* Учтены изменения PostgreSQL 18+ (PGDATA, volume mount)
* Инициализация БД вручную или через ConfigMap

---

## Асинхронное взаимодействие

### RabbitMQ

Используется для:

* событий при создании/изменении отзывов
* слабосвязанного взаимодействия сервисов

Включён Management UI:

* `http://localhost:15672`

---

## Observability

### Actuator

* `/actuator/health`
* Health Indicators
* CircuitBreaker Health

---

### Zipkin

* Полная трассировка запросов
* Интеграция через Micrometer Brave
* Endpoint:

    * `http://localhost:9411`

---

## Отказоустойчивость

### Resilience4j

Настроено:

* Sliding Window
* Failure Rate Threshold
* Half‑Open State
* Retry
* Rate Limiting

Интеграция с Actuator Health.

---

## Контейнеризация

### Docker Compose

Используется для локального запуска всей системы:

* Postgres + pgAdmin
* RabbitMQ
* Zipkin
* Eureka
* Config Server
* Все микросервисы

Сервисы объединены в изолированные сети:

* `microservice-network`
* `postgres`

---

### Kubernetes (Minikube)

* StatefulSet для PostgreSQL
* Service (ClusterIP)
* ConfigMap для конфигураций
* Работа с PVC и PV

Отработаны сценарии:

* CrashLoopBackOff
* Volume migration
* Lifecycle PostgreSQL Pod

---

## Технологический стек

* **Java 25**
* **Spring Boot 4.0.1**
* **Spring Cloud 2025.1.0**
* Spring Data JPA
* Spring Cloud Gateway
* Eureka
* OpenFeign
* Resilience4j
* Micrometer + Zipkin
* RabbitMQ
* PostgreSQL
* Docker / Docker Compose
* Kubernetes / Minikube

---

## Цель проекта

Проект создан как:

* учебный production‑level пример
* демонстрация микросервисных паттернов
* база для дальнейшего масштабирования

Подходит для:

* pet‑project
* портфолио backend‑разработчика
* экспериментов с Cloud Native архитектурой

---

## Возможные улучшения

* Security (OAuth2 / Keycloak)
* Saga / Event Sourcing
* CI/CD pipeline
* Centralized logging (ELK / Loki)
* Horizontal Pod Autoscaling

---

## Автор

**Maksim Kazłoŭ**

Проект развивается и расширяется по мере изучения микросервисной экосистемы.
