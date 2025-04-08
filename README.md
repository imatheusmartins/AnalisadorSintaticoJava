# Analisador Sintático em Java

## Gramática Fornecida

E → E + T | E - T | T

T → T * F | T / F | F

F → ( E ) | id

Para implementação de um analisador sintático LL(1) é necessário reescrever essa gramática para acabar com a recursão a esquerda.

Rescrevendo a gramática, temos

E  → T E'

E' → + T E' | - T E' | ε

T  → F T'

T' → * F T' | / F T' | ε

F  → ( E ) | id

## Conjuntos Fisrt e Follow:
### A partir da nova gramática, temos os seguintes conjuntos First e Follow

### First:

- **First(E)** = First(T) = { `(`, `id` }
- **First(E')** = { `+`, `-`, `ε` }
- **First(T)** = First(F) = { `(`, `id` }
- **First(T')** = { `*`, `/`, `ε` }
- **First(F)** = { `(`, `id` }

### Follow:

- **Follow(E)** = { `)`, `$` }  <!-- `$` representa o fim da entrada -->
- **Follow(E')** = { `)`, `$` }
- **Follow(T)** = { `+`, `-`, `)`, `$` }
- **Follow(T')** = { `+`, `-`, `)`, `$` }
- **Follow(F)** = { `*`, `/`, `+`, `-`, `)`, `$` }

## Como executar o projeto
- execute o comando: _javac *.java_ para compilar os arquivos Java
- execute o comando: _java Main_ para iniciar o programa.

#### Após executar estes comandos o usuário digita uma expressão, como no exemplo mostrado no terminal e o analisador verifica se ela é válida ou não.
