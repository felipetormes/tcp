# sortPaperByGrade

## Dados

Artigos |
--- |
1,A1,R1,C1,T1 |
2,A2,R1,C1,T1 |
3,A3,R3,C1,T1 |
4,A4,R4,C1,T1 |
5,A5,R4,C1,T1 |
6,A6,R6,C1,T1 |
7,A7,R6,C1,T1 |

Pesquisadores |
--- |
1,R1,UFRGS,T1 |
2,R2,UFMG,T1 |
3,R3,UFSM,T1 |
4,R4,PUCRS,T1 |
6,R6,UFRJ,T1 |

Conferências |
--- |
C1,1,2,4,6 |

Atribuições (revisão) |
--- |
1,2,0 |
1,3,2 |
2,4,1 |
2,3,3 |
3,1,3 |
4,6,3 |
5,6,-1 |
6,1,0 |
7,1,-2 |

## Teste

- ordenar decrescente: 4,3,2,1,0,5,7
- ordenar crescente: 7,5,0,1,2,3,4

# setGrade

## Dados

Mesmos de antes

## Testes

- atribuir 3 do R1 para o A7 e ver se a ordem é 7,4,3,2,1,5
- atribuir -5 do R2 para A1 e ver se falha (não entre [-3,3])
- atribuir 5 do R2 para A1 e ver se falha (não entre [-3,3])
- atribuir 2 do R4 para A4 e ver se falha (R4 não é revisor de A4)

# getAverageGrade

## Dados

Mesmos de antes

## Teste

- getAverageGrade(1) == 1
- getAverageGrade(2) == 2
- getAverageGrade(3) == 3
- getAverageGrade(4) == 3

# hasPendingReviews

## Dados

Mesmos, menos as atribuições:

Atribuições (revisão) |
--- |
1,2,0 |
1,3,2 |
2,4,1 |
2,3,3 |
3,1,3 |
4,6,3 |
5,6,-1 |
7,1, |

## Testes

- hasPendingReviews(7) == true
- hasPendingReviews(6) == true
- hasPendingReviews(1) == false
