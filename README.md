# 🍞 SALVO - Pão Dormido

Sistema de redistribuição de alimentos excedentes, conectando estabelecimentos alimentícios ao público geral e comunidades carentes.

## 📋 Sobre o Projeto

O SALVO é uma aplicação Java que simula uma plataforma no modelo "Food To Save", onde estabelecimentos cadastram alimentos excedentes com preço reduzido, reduzindo o desperdício e gerando impacto social.

## 🏗️ Estrutura do Projeto

src/
├── entities/
│   ├── Usuario.java      # Classe base abstrata
│   ├── Cliente.java      # Usuário comprador
│   ├── Loja.java         # Estabelecimento vendedor
│   ├── Produto.java      # Item disponível
│   ├── Promocao.java     # Oferta com preço reduzido
│   └── Pedido.java       # Pedido realizado
└── service/
├── ClienteService.java
├── LojaService.java
└── PedidoService.java

## 👥 Time

- Anderson
- Marcos
- Wesley

## 🚀 Como Rodar

1. Clone o repositório
2. Abra no IntelliJ IDEA
3. Execute o arquivo `Main.java`