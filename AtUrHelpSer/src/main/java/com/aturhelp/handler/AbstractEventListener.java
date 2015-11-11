package com.aturhelp.handler;

public abstract class AbstractEventListener {

	   
    public int hashCode() {
        return this.getClass().getName().hashCode();
    }

   
    public boolean equals(Object obj) {
        if(!(obj instanceof AbstractEventListener)){
            return false;
        }

        return this.getClass().getName().equals(obj.getClass().getName());
    }

    public abstract void consume(AbstractEventObject eventObject) throws Exception;
}
