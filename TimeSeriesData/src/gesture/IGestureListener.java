/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesture;

import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface IGestureListener extends EventListener{
     void gestureActionPerformed(GestureEvent ge);
}
