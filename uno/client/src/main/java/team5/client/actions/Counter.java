/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.actions;

/**
 *
 * @author андрей
 */
public class Counter {
    private int count;

    public Counter(int i){
        count = i;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public Counter (){
        count = 0;
    }
    
    public void inc(){
        count++;
    }
    public void dec(){
        count--;
    }
    
    
}
