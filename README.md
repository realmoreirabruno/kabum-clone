# Kabum Clone - Android E-commerce App

Este é um aplicativo Android de e-commerce inspirado no layout e funcionalidades do site da Kabum.
O projeto foi desenvolvido com foco em aprendizado e demonstração de habilidades em arquitetura moderna Android,
persistência local, consumo de APIs REST e boas práticas de desenvolvimento mobile.

---

## Funcionalidades

- Single Activity App, utilizando Composables para as outras telas
- Listagem de produtos via API REST
- Armazenamento local com Room Database
- Marcar/desmarcar produtos como favoritos
- Tela de destaques com carrossel animado
- Recomendados com rolagem horizontal
- Design responsivo com Jetpack Compose
- Navegação entre telas com Jetpack Navigation
- Injeção de dependência com Koin

---

## Lista de Tecnologias

| Camada                 | Tecnologia                                |
|------------------------|-------------------------------------------|
| Linguagem              | Kotlin                                    |
| UI                     | Jetpack Compose                           |
| Ger. de Estado         | StateFlow, LiveData                       |
| Arq. de Projeto        | MVVM (Model-View-ViewModel)               |
| Navegação              | Jetpack Navigation Compose                |
| Injeção de Dependência | Koin                                      |
| Persistência local     | Room Database                             |
| Consumo de API         | Retrofit + Gson                           |
| Image loading          | Coil                                      |
| ViewModel Scope        | Coroutines                                |
| API Fake               | https://fake-store-api.mock.beeceptor.com |

---

## Estrutura do Projeto
O projeto foi desenvolvido com a arquitetura MVVM:
├── data
│├── local
│ ├── dao
│ ├── database
│ └── entity
│├── remote
│├── model
│└── repository
├── ui
│ ├── navigation
│ ├── components
│ └── screens
├── viewmodel
└── dependencyinjection

---

## Instalação e Execução

1. Clone o repositório:

```git clone https://github.com/realmoreirabruno/kabum-clone.git```

2. Abra o projeto no Android Studio

3. Aguarde o Gradle sincronizar e execute o app em um emulador ou dispositivo real

## Observações Técnicas

Os produtos são carregados da API fake e armazenados localmente com Room.

Favoritar um produto persiste essa ação no banco local.

O app tenta manter os favoritos ao atualizar os dados da API.

Caso o app seja fechado e reaberto, os dados permanecem salvos.