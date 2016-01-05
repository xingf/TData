/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesture;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class GestureEventManager {
    
    private static GestureEventManager gestureManager = new GestureEventManager();
    private Collection listeners;
    
    private GestureEventManager(){ 
    }
    
    static public GestureEventManager getGestureManager(){
        if(gestureManager == null){
            gestureManager = new GestureEventManager();
        }
        return gestureManager;
    }
    
    public void addGestureListener(IGestureListener listener){
        if(this.listeners == null){
            this.listeners = new HashSet();
        }
        this.listeners.add(listener);
    }
    
    public void removeGestureListener(IGestureListener listener){
        if(this.listeners != null){
            this.listeners.remove(listener);
        }
    }
    
    public void notifyListener(GestureEvent event){
        Iterator iter = this.listeners.iterator();
        while(iter.hasNext()){
            IGestureListener listener = (IGestureListener)iter.next();
            try{
            listener.gestureActionPerformed(event);
            }catch(Exception e){
                
            }
        }
    }
}
