/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listclass;

/**
 *
 * @author Jim
 */
public class ListClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int MAX_POINTS = 10000;
        final double MAX_VAL = 10.0;
        double tmpX, tmpY, minima, tmpMin, average;
        minima = (MAX_POINTS + 1) * MAX_VAL;
        average = 0.0;
        TSPlist.Node tmpNode, tmpClosest;
        TSPlist masterList = new TSPlist();
        TSPlist workingList = new TSPlist();
        
        for (int i = 0; i < MAX_POINTS; i++){
            tmpX = MAX_VAL * Math.random();
            tmpY = MAX_VAL * Math.random();
            masterList.addNodeToListEnd(tmpX, tmpY);
        }
        masterList.printList();
        System.out.println("Master Distance:" +(masterList.calcListDistance()));
        
        for (int i = 0; i<MAX_POINTS;i++){
            workingList.delete();
            TSPlist.copy(masterList, workingList);
            //System.out.println("before Distance:" +(workingList.calcListDistance()));
            workingList.swapNodes(0, i);
            //workingList.printList();

            tmpNode = workingList.getListHead();
            while (tmpNode != null && tmpNode.next != null){
                tmpClosest = workingList.searchListForClosest(tmpNode);
                if (tmpClosest != null) workingList.swapNodes(tmpNode.next, tmpClosest);
                tmpNode = tmpNode.next;
            }
            //startingList.printList();
            System.out.println("Greedy Distance " + i + " : " +(tmpMin = workingList.calcListDistance()));
            average += tmpMin;
            if (tmpMin < minima)minima = tmpMin;
        }
        System.out.println("Average: " + (average/MAX_POINTS));
        System.out.println("Minima: " + minima);
    }
    
}
