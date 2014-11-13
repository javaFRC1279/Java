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
public class TSPlist {
    
    private Node listHead;
    private Node listTail;
    private int listSize;
    
    public TSPlist(){
        listHead = null;
        listTail = null;
        listSize = 0;
    }
        
    static void copy(TSPlist srcList, TSPlist destList){
        
        if (srcList == null || destList == null) return;
        if (destList.listHead != null) return;
        
        Node tmpNode = srcList.listHead;
        while(tmpNode != null){
            destList.addNodeToListEnd(tmpNode.x, tmpNode.y);
            tmpNode = tmpNode.next;
        }     
    }
    
    void delete(){
        this.listHead = null;
        this.listTail = null;
        this.listSize = 0;    
    }
    
    void addNodeToListEnd(Node newNode){
        listTail.next = newNode;
        listTail = newNode;
        if (listHead == null) listHead = listTail;
        listSize++;
    }
    
    void addNodeToListEnd(double x, double y){
        Node newNode = new Node(x, y);
        if (listTail == null){
            listHead = newNode;
            listTail = newNode;
        } else {
            listTail.next = newNode;
            listTail = newNode;
        }
        listSize++;
    }
        
    void removeFromList(Node junkNode){
        if (junkNode == null) return;//never initialized
        if (junkNode.next == null && junkNode.prev == null) return;//not in a list
        if (junkNode.next == null){
            //last in list
            listTail = junkNode.prev;
        } else if (junkNode.prev == null){
            //first in list
            listHead = junkNode.next;
        } else {
            //middle of list
            (junkNode.prev).next = junkNode.next;
            (junkNode.next).prev = junkNode.prev;
            junkNode.next = null;
            junkNode.prev = null;
        }
        listSize--;
    }
    
    void insertAfterNode(Node prev, Node newNode){
        if (prev == null && listHead == null){
            //new list
            listHead = newNode;
            listTail = newNode;
        } else if (prev == null){
            //new head
            newNode.next = listHead;
            listHead = newNode;//order important!
        } else {
            //error
            return;//don't change sise
        }
        listSize++;
    }
        
    void insertBeforeNode(Node next, Node newNode){
        if (next == null && listTail == null){
            //new list
            listHead = newNode;
            listTail = newNode;
        } else if (next == null){
            //new tail
            newNode.prev = listTail;
            listTail = newNode;//order important!
        } else {
            //error
            return;//don't change sise
        }
        listSize++;
    }
    
    Node searchListForClosest(Node start){
        Node found, tmpNode;
        double tmpDistance, distance = 99.0;
        
        if (start == null) return null;
        
        
        tmpNode = start;
        found = start.next;
        while (tmpNode != null && tmpNode.next != null){
            if ((tmpDistance = calcDistance(start, found)) < distance){
                found = tmpNode;
                //System.out.println("distance: " + distance);
            }
            tmpNode = tmpNode.next;
        }
        //if (found != null) System.out.println("x: " + found.x + " y: " + found.y);
        return found;
    }
    
    void swapNodes(Node a, Node b){
        if (a == null || b == null) return;

        double tmpX = a.x;
        double tmpY = a.y;
        
        a.x = b.x;
        a.y = b.y;
        b.x = tmpX;
        b.y = tmpY;

    }
    
    void swapNodes(int aIndex, int bIndex){
        Node a, b;
        if (aIndex == bIndex) return;
        a = this.listHead;
        for (int i = 0;i < aIndex;i++){
            a = a.next;
        }
        b = this.listHead;
        for (int i = 0;i < bIndex;i++){
            b = b.next;
        }
        double tmpX = a.x;
        double tmpY = a.y;
        
        a.x = b.x;
        a.y = b.y;
        b.x = tmpX;
        b.y = tmpY;
    }
    
    double calcDistance(Node fromNode, Node toNode){
        if (fromNode == null || toNode == null) return 99.0;
        double xSquared, ySquared;
        xSquared = (fromNode.x - toNode.x)*(fromNode.x - toNode.x);
        ySquared = (fromNode.y - toNode.y)*(fromNode.y - toNode.y);
        return (Math.sqrt(xSquared + ySquared));
    }
    
    void printList(){
        Node tmp = listHead;
        int i = 0;
        while (tmp != null){
            System.out.format("Node %d x:%5.2f y:%5.2f", i, tmp.x, tmp.y);
            tmp = tmp.next;
            i++;
            System.out.println();
        }
        System.out.println();
    }
    
    double calcListDistance(){
        Node tmp = listHead;
        if (tmp == null) return 0.0;
        double total = 0.0;
        while (tmp.next != null){
            total += calcDistance(tmp, tmp.next);
            tmp = tmp.next;
        }
        return total;
    }
    
    public Node getListHead(){ return listHead;}
    
    public Node getListTail(){ return listTail;}
    
    public class Node{ 
        double x, y;
        Node next;
        Node prev;
        
        public Node(double x, double y){
            this.x = x;
            this.y = y;
            next = null;
            prev = null;
        }
    }
    
}
