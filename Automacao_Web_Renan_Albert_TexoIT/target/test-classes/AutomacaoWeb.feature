#language: pt

Funcionalidade: Acessar e validar dados em https://jsonplaceholder.typicode.com/

  Cenário: Acessar a página Guide e abrir o link /albums/1/photos
    Dado que estou na página inicial do https://jsonplaceholder.typicode.com/guide/
    Quando clico no menu "Guide"
    E seleciono o link "/albums/1/photos"
    Então devo ser redirecionado para a página /albums/1/photos

  Cenário: Capturar e salvar os dados exibidos em tela em um array JSON
    Dado que estou na página /albums/1/photos
    Quando capturo os dados exibidos na tela
    E salvo esses dados em um array JSON
    Então devo ter um array JSON com os dados capturados

  Cenário: Validar os dados do objeto com id = 6
    Dado que tenho um array JSON com os dados capturados
    Quando busco pelo objeto com id = 6
    Então devo encontrar um objeto com id = 6
    E os dados desse objeto devem ser válidos conforme esperado
