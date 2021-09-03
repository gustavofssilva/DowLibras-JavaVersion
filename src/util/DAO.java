/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Collection;


public interface DAO <BEAN> {
    public boolean insere(BEAN obj);
    public boolean remove(BEAN obj);
    public boolean altera(BEAN obj);
    public BEAN buscaID(BEAN obj);
    public Collection<BEAN> lista(String criterio);
}
