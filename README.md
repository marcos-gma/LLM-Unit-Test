# LLM-Unit-Test

Este projeto realiza um aprofundamento no artigo "Test Intention Guided LLM-Based Unit Test Generation" (ICSE 2025), explorando a geração de testes unitários com LLMs a partir da intenção do código. A proposta estende o estudo original ao testar diferentes modelos de linguagem (LLMs) e múltiplas linguagens de programação, avaliando a qualidade, cobertura e relevância dos testes gerados em cada cenário. O objetivo é comparar a eficácia dos modelos e entender como a escolha da linguagem influencia os resultados.

## Como rodar o projeto

### Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/)

### Instalação

Clone o repositório e acesse a pasta do projeto:

```bash
git clone https://github.com/seu-usuario/LLM-Unit-Test.git
cd LLM-Unit-Test
```

### Executando os testes

Para rodar todos os testes unitários:

```bash
mvn test
```

Para rodar apenas uma classe de teste específica (por exemplo, `ExemploTest`):

```bash
mvn test -Dtest=ExemploTest
```

### Observações

- Certifique-se de que o Maven e o Java estejam corretamente instalados e configurados no seu PATH.
