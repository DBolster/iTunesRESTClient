/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResultTypes;

import com.google.gson.JsonObject;

/**
 *
 * @author douglas.bolster@student.sl.on.ca
 */
public class OuterResult {

        Integer resultCount;
        JsonObject[] results;

        public Integer getResultCount() {
            return resultCount;
        }

        public void setResultCount(Integer resultCount) {
            this.resultCount = resultCount;
        }

        public JsonObject[] getResults() {
            return results;
        }

        public void setResults(JsonObject[] results) {
            this.results = results;
        }    
}
