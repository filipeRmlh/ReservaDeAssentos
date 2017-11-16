
b = [1,[]]

#Operacao de reserva de assento escolhido
def op3 (u, p, l):
    temp = b[1]
    if temp[p-1] == 0:
        temp[p-1] = u
        teste (l, temp)
    else:
        print ('Assento reservado por outro usuario')


#Operacao de reserva de assento randomico
def op2 (u, pr, l):
    temp = b[1]
    for i in xrange(len(temp)):
        if temp[i] == 0:
            op3(u, pr, l)
            break
        elif i == len(temp):
            print ('Nao ha assentos disponiveis')
    

    
#Operacao de liberacao de assento
def op4 (u, p, l):
    temp = b[1]
    if temp[p-1] == u:
        temp[p-1] = 0
        teste (l,temp)
    else:
        print ('Impossivel liberar assento. Assento reservado por outro usuario')


#Operacao de visualizacao de assentos
def op1 (u, l):
    teste (l,b[1])


#Verificao de log
def teste (l,res):
    for i in xrange(len(l)):
        if l[i] != res[i]:
            raise ValueError('Ocorreu um erro durante a execucao. Logs inconsistentes!', b[0])
    b[0]+=1
    b[1] = res


#Inicializacao do buffer
def init (n):
    b[1] = [0] * n