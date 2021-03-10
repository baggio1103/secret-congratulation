package org.baggio;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {

    Node root;
    String text;

    public HuffmanTree(String text){
        this.text = text;
        root = null;
    }

    public HuffmanTree(){
        this.text = "As Mark tweeted today, there used to be a page on Wikipedia listing pangrams " +
                "in various languages. This was deleted yesterday for the kind of reasons that only " +
                "Wikipedians have (It is mostly comprised of nonsense phrases thought up by people who " +
                "apparently find this sort of thing terribly clever).\n" +
                "\n" +
                "Pangrams are words or sentences containing every letter of the alphabet at least once;" +
                " the best known English example being A quick brown fox jumps over the lazy dog. As well as" +
                " containing some rather amusing gems, the pangrams in languages other than English can be " +
                "occasionally useful for designers of all sorts. For that reason I have resurrected the page of " +
                "pangrams here, pretty much as it was in Wikipedia." +
                "ABCDEFGHIJKLMNOPQRSUVWXYZ1234567890!?:();ABCDEFGHIJKLMNOPQRSUVWXYZ1234567890!?:)";
        root = null;
    }


    // calcFrequency() - receives some text as an argument and calculates a frequency of each character
    public static HashMap<Character, Integer> calcFrequency(String text){
        HashMap<Character, Integer> map = new HashMap<>();
        int count = 1;
        for (int i = 0; i < text.length(); i++){
            if (!map.containsKey(text.charAt(i))) {
                for (int j = 0; j < text.length(); j++) {
                    if ((text.charAt(i) == text.charAt(j)) && (i != j)) {
                        count++;
                    }
                }
                map.put(text.charAt(i), count);
                count = 1;
            }
        }
        return map;
    }

    public void constructTree(){
        HashMap<Character, Integer> map = calcFrequency(text);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new MyComparator());

        for (Map.Entry<Character, Integer> entry : map.entrySet()){       //adding Nodes to the priorityQueue
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));//Nodes represent single characters and its frequency
        }

        while (priorityQueue.size() > 1){
            Node node1 = priorityQueue.poll();
            Node node2 = priorityQueue.poll();
            Node newNode = new Node(node1.frequency + node2.frequency);
            newNode.left = node1;
            newNode.right = node2;
            priorityQueue.add(newNode);
        }
        root = priorityQueue.peek(); // Huffman tree is constructed
    }

    public void traverseTree(Node node, String path, HashMap<Character, String> codewords){
        if (node.left == null && node.right == null){
            codewords.put(node.character, path);
            return;
        }
        traverseTree(node.left, path +"0", codewords);
        traverseTree(node.right, path+"1", codewords);
    }

    public String encode(String string){ //encoding is possible after the tree has been constructed
        HashMap<Character, String> codewords = new HashMap<>();
        traverseTree(root, "", codewords);
        String encodedText = "";
        for (int i = 0; i < string.length(); i++){
            encodedText += codewords.get(string.charAt(i));
        }
        return encodedText;
    }

    public String decode(String string){
        Node node = root;
        String decodedText = "";
        for (int i = 0; i < string.length(); i++){
            if (string.charAt(i) == '1'){
                node = node.right;
            }else {
                node = node.left;
            }
            if (node.left == null && node.right == null){
                decodedText += node.character;
                node = root;
            }
        }
        return decodedText;
    }

}

class Node{
    int frequency;
    Character character;
    Node left;
    Node right;
    public Node(char character, int frequency){
        this.character = character;
        this.frequency = frequency;
        left = right = null;
    }

    public Node(int frequency){
        this.frequency = frequency;
    }

}

class MyComparator implements Comparator<Node>{
    public int compare(Node node1, Node node2) {
        return node1.frequency - node2.frequency;
    }
}