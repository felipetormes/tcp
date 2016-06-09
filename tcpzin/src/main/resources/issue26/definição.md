# chooseBestCandidate

O melhor candidato é aquele com:
- menor número de artigos alocados até o momento
- em caso de empate, o com menor id

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
C1,1,2,3,4,6 |

Atribuições (revisão) |
--- |
1,2, |
1,3, |
2,4, |
2,3, |
3,1, |
4,6, |
5,6, |

## Testes

- chooseBestCandidate(1) == 4

# selectPapersByAverage

## Dados

Mesmos de antes, mas com estas atribuições:

Atribuições (revisão) |
--- |
1,2,0 |
1,3,2 |
2,4,1 |
2,3,3 |
3,1,3 |
4,6,3 |
5,6,-1 |
7,1,-2 |

## Teste

- Deve resultar em uma lista 4,3,2,1,7,5
