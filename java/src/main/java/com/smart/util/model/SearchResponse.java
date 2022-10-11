
package com.smart.util.model;
import java.util.ArrayList;
import java.util.List;

public class SearchResponse{
    
    
    public SearchResponse(List l,long m){
        this.data = l;
        this.count = m;
    }

    private List data = new ArrayList();
    private Long count;

    public void setData(List data){
         this.data = data;
    }

    public List getData(){
        return this.data;
   }

   public void setCount(Long count) {
       this.count = count;
   }

   public Long getCount() {
    return this.count;
}


}
