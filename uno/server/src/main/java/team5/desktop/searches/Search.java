/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.searches;

import java.util.List;

/**
 *
 * @author Dmitry
 */
public interface Search {
    public  List regularSearch(String request);//нужен static, но его нельзя засунуть в интерфейс
}
