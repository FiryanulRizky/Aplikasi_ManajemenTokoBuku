/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM_Toko_Buku;

import java.util.Date;
/**
 *
 * @author Rasito7659
 */
public class cekClass {
    
    public static void main(String[] args){
        
        Date uDate = new Date();
        System.out.println(""+uDate);
        java.sql.Date sDate = convertUtilToSql(uDate);
        System.out.println(""+sDate);
        
    }
    
    private static java.sql.Date convertUtilToSql(java.util.Date uDate){
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
}
