/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_pkg;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Евгений
 */
public class HQueryClass {
    
    Session session =null;
    
    public HQueryClass(){
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
   // getting row of Object table by Object.object_id 
    public Objects getObjectById(int Id){
        Objects object=null;
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q =session.createQuery("from Objects as a where a.objectId="+Id);
            object=(Objects)q.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return object;
    }
    
    
    // getting text params value by object_id and attribute_id
    public String getTextValue(int Object_id, int Attr_id){
       String responce="";
       try{
           org.hibernate.Transaction tx=session.beginTransaction();
           Query q =session.createQuery("query");
           responce=(String)q.uniqueResult();
       }catch(Exception e){
           e.printStackTrace();
       }
       return responce;
    }
    
    // getting number params value by object_id and attribute_id
    public Integer getNumberValue(int Object_id, int Attr_id){
       Integer responce=null;
       try{
           org.hibernate.Transaction tx=session.beginTransaction();
           Query q =session.createQuery("query");
           responce=(Integer)q.uniqueResult();
       }catch(Exception e){
           e.printStackTrace();
       }
       return responce;
    }
}

