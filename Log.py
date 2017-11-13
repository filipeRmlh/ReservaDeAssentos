from teste import *

a = [0,0,0,0,0]
r = [1,[]]

#Log 1
op1 (a,1,1)
r[1] = [1,0,0,0,0]
teste (a,r)

#Log 2
op2 (a,3,2)
r[1] = [1,3,0,0,0]
teste (a,r)

#Log 3
op1 (a,4,4)
r[1] = [1,3,0,4,0]
teste (a,r)

#Log 4
op3 (a,1,1)
r[1] = [1,3,0,4,0]
teste (a,r)

#Log 5
op4 (a,4)
r[1] = [0,3,0,4,0]
teste (a,r)
