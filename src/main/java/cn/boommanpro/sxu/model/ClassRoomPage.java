package cn.boommanpro.sxu.model;

import lombok.Data;
import me.ghui.fruit.annotations.Pick;

import java.util.List;

@Data
public class ClassRoomPage {

    @Pick("table")
    public List<Table> table;



    @Data
    public static class Table{
        @Pick("tr")
        public List<Td> trList;

        public int getColumnNumber(){
            try {
                return trList.get(0).getTdList().size();
            }catch (Exception e){
                return 0;
            }
        }

        @Data
        public static class Td{
            @Pick("td")
            //String中是每一列Cell
           public List<String> tdList;
        }
    }

}
