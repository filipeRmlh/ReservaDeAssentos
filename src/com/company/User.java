package com.company;

public class User extends Thread{
    public int id;
    private LogManager log;
    protected Assento[] assentos;
    private Semaphore semaphore;

    private Assento myAssento;

    User(int id,Assento[] assentos,LogManager log, Semaphore semaphore){
      this.log=log;
      this.id=id;
      this.assentos=assentos;
      this.semaphore = semaphore;
    }

    private boolean alocaAssentoMaster(Assento assento){
        boolean retorno;
        try {
            this.semaphore.take();
            retorno = assento.setUser(this);
            if(retorno)this.myAssento=assento;
            return retorno;
        }catch (InterruptedException ex){
            System.err.println("Erro ao alocar Assento dado");
            return false;
        }
    }

    //***********************************************

    public boolean alocaAssento(Assento assento){
        boolean retorno = this.alocaAssentoMaster(assento);
        this.log.alocarAssentoDado(this.id,assento.id,this.assentos);
        return retorno;
    }

    public boolean alocaAssento(Assento[] assentos){
        boolean retorno;
        for (Assento assento:assentos) {
            if(assento.getUser()==null){
                retorno = this.alocaAssentoMaster(assento);
                if(retorno)this.log.alocarAssentoLivre(this.id,assento.id,assentos);
                return retorno;
            }
        }
        return false;
    }
    public int[] visualizaAssentos(Assento[] assentos){
        int[] myassentos = new int[assentos.length];
        for (int i=0;i<assentos.length;i++) {
            int userId = assentos[i].getUser()==null?0:assentos[i].getUser().id;
            myassentos[i]=userId;
        }
        this.log.visualizarAssento(this.id,myassentos);
        return myassentos;
    }
    public boolean liberaAssento(Assento assento){
        boolean retorno;
        try{
            retorno = this.myAssento.clearUserId(this.id);
            if(retorno)this.myAssento = null;
            this.semaphore.release();
            return retorno;
        }catch (InterruptedException ex){
            return false;
        }
    }
}