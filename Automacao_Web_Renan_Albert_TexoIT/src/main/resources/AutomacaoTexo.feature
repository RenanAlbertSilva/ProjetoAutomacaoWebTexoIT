Feature: Capture e validar dados em um site usando automação web

  Scenario: Capture e validar dados do site JSONPlaceholder
    Given Que initialize o navegador e acesso o site recomendado
    When Eu access o menu Guide
    And Eu nave até o link de fotos
    Then Eu capture todos os dados exibidos em tela e os salvo em um array JSON validando o ID = 6