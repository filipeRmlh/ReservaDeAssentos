
#n = numero de assentos



#Operacao de reserva de assento escolhido
def op1 (a, u, p):
    if a[p-1] == 0:
        a[p-1] = u
    else:
        print ('Assento reservado por outro usuario')


#Operacao de reserva de assento randomico
def op2 (a, u, pr):
    for i in xrange(len(a)):
        if a[i] == 0:
            break
        elif i == len(a):
            print ('Nao ha assentos disponiveis')
    op1(a,u,pr)

    
#Operacao de liberacao de assento
def op3 (a, u, p):
    if a[p-1] == u:
        a[p-1] = 0
    else:
        print ('Impossivel liberar assento. Assento reservado por outro usuario')


#Operacao de visualizacao de assentos
def op4 (a,u):
    print (a)


#Verificao de log
def teste (a,r):
    res = r[1]
    for i in xrange(len(a)):
        if a[i] != res[i]:
            raise ValueError('Ocorreu um erro durante a execucao. Logs inconsistentes!', r[0])
    r[0]+=1

import Log
