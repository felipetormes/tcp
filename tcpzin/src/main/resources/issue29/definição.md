# isSuitedToReview

Artigo: id, título, autor, conferência, tópico
Pesquisador: id, nome, afiliação, tópicos
Revisão: artigo, revisor, nota

Um pesquisador P pode revisar um artigo A se:
1. A.autor != P
2. A.autor.afiliação != P.afiliação
3. A.tópico pertence a P.tópicos
4. Não existe revisão R tal que
	- R.autor = P && R.artigo = A

## Dados

Artigos |
--- |
1,A1,R1,C1,T1 |
2,A2,R2,C1,T1 |
3,A3,R1,C1,T2 |
4,A4,R4,C1,T1 |
5,A5,R5,C1,T3 |

Pesquisadores |
--- |
1,R1,UFRGS,T1,T2 |
2,R2,UFMG,T1 |
4,R4,UFRGS,T1,T2 |
5,R5,UFMG,T3 |

Conferências |
--- |
C1,1,2,4 |

Atribuições (revisão) |
--- |
1,2,3 |

## Testes para passar

1. Atribuir A2 para R4
1. Atribuir A4 para R2

## Testes para falhar

1. Atribuir A1 para R1 (viola 1)
1. Atribuir A1 para R4 (viola 2)
1. Atribuir A5 para R1 (viola 3)
1. Atribuir A1 para R2 (viola 4)

# Reviews

Um pesquisador P revisa um artigo A se existe uma revisão R tal que

- R.autor = P && R.artigo = A

## Dados

Mesmos de antes

## Testes para passar

1. R2 revisa A1 == true
1. R1 revisa A2 == false

# setReviewer

## Dados

Mesmos

## Testes

1. criar revisão de A2 para R4 e verificar se R4 é revisor de A2
