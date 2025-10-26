📱 App Sicredi Onboarding

Aplicativo Android desenvolvido em Kotlin + Jetpack Compose, com arquitetura MVVM e navegação Compose, simulando o processo de cadastro e gerenciamento de associados.

🚀 Tecnologias

Kotlin 2.0

Jetpack Compose (Material 3)

Navigation Compose

ViewModel / StateFlow

Coroutines

Android Studio Iguana ou superior

Gradle 8.5+

🧩 Estrutura do Projeto
app_sicredi/
├── app/
│   ├── src/main/java/com/sicredi/onboarding/
│   │   ├── data/              → Repositórios e modelos
│   │   ├── ui/screens/        → Telas Compose (Login, Lista, Formulário)
│   │   ├── navigation/        → Rotas e NavGraph
│   │   ├── viewmodel/         → ViewModels (Login, Associate)
│   │   └── MainActivity.kt    → Ponto de entrada
│   └── res/                   → Recursos (ícones, cores, temas)

⚙️ Como executar o projeto
1. Pré-requisitos

Android Studio Koala ou superior

Kotlin 2.0+

SDK Android 14 (API 34)

JDK 17

2. Clonar o repositório
   git clone https://github.com/seu-usuario/app_sicredi.git
   cd app_sicredi

3. Abrir no Android Studio

Abra o Android Studio → File → Open → selecione a pasta app_sicredi

Aguarde o Gradle Sync completar (a primeira vez pode demorar um pouco)

Se solicitado, aceite a instalação das dependências ou plugins Kotlin/Compose

4. Executar o projeto

No topo da IDE, selecione o dispositivo virtual (AVD) ou celular físico conectado

Clique em ▶️ Run ‘app’

Se tudo estiver certo, o app será instalado e aberto automaticamente.

🧠 Funcionalidades
Tela	Descrição
Login	Tela inicial de autenticação (dummy)
Lista de Associados	Exibe todos os associados cadastrados
Cadastro / Edição	Formulário para criar ou editar um associado
Exclusão	Permite remover um registro existente
🧰 Estrutura MVVM Simplificada

AssociateViewModel → lógica de negócio e persistência via AssociateRepository

AssociateListScreen / AssociateFormScreen → interface Compose reativa

NavGraph → define as rotas (Login, List, Form)

🧪 Teste rápido

Rode o app

Faça login (sem necessidade de credenciais)

Clique em Adicionar Associado

Preencha os campos (datas aceitam dd/MM/yyyy ou ddMMyyyy)

Salve → o novo associado aparece na lista

⚠️ Observações

Caso veja o erro do Compose Compiler Gradle Plugin, garanta que a versão do Kotlin e Compose estão alinhadas no build.gradle.

Em builds Kotlin 2.0+, o plugin Compose Compiler é obrigatório:

plugins {
id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

📄 Licença

Este projeto é apenas para fins educacionais.
Sinta-se livre para clonar, modificar e evoluir! 🚀