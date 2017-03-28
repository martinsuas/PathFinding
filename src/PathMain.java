import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class PathMain {
    public static void main(String args[]) throws Exception{
        if (args.length == 2 &&
                (args[0].equals("a_star") || args[0].equals("bfs") || args[0].equals("backtracking"))) {
            File f1 = new File(args[1]);
            System.out.println(args[1]);
            Scanner s = new Scanner(f1);

            // Create graph while reading file.
            Graph g = new Graph();
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(",");
                g.add_edge(line[0], line[1], Integer.parseInt(line[2]));
            }


            switch (args[0]) {
                case "bfs":
                    System.out.println("Performing BFS.\n" + g.BFS("MON", "GOL"));
                    break;
                case "backtracking":
                    System.out.println("Performing backtracking.\n" + g.Backtracking("MON", "GOL"));
                    break;
                case "a_star":
                    System.out.println("Performing A*.\n" + g.A_Star("ENG", "GOS", heuristicA()));
                    break;
                default:
                    throw new Exception("This code should be unreachable.");
            }
        } else {
            System.out.println("Usage: PathMain [a_star|bfs|backtracking] $filename");
        }
    }

    public static HashMap<String, Integer> heuristicA() {
        HashMap<String, Integer> r = new HashMap<>();
        r.put("GVE", 90);   r.put("GVD", 75);   r.put("GVC", 122);  r.put("GVP", 53);
        r.put("CRS", 61);   r.put("HLC", 89);   r.put("COL", 65);  r.put("ROS", 119);
        r.put("LOW", 118);   r.put("GOS", 85);   r.put("WAL", 105);  r.put("LBR", 107);
        r.put("BOO", 92);   r.put("UNI", 99);   r.put("VIG", 103);  r.put("GAN", 123);
        r.put("BLC", 140);   r.put("SAN", 95);   r.put("CAR", 94);  r.put("CBT", 41);
        r.put("INS", 37);   r.put("ENG", 10);   r.put("GLE", 20);  r.put("ORN", 26);
        r.put("GOL", 0);   r.put("ENT", 13);   r.put("USC", 15);  r.put("SIH", 22);
        r.put("SLA", 47);   r.put("SUS", 39);   r.put("MON", 131);  r.put("EAS", 129);
        r.put("POL", 143);   r.put("SAU", 142);   r.put("CPC", 150);  r.put("SMT", 162);
        r.put("CLK", 158);   r.put("RIA", 173);   r.put("HAC", 160);  r.put("AUG", 168);
        r.put("GWH", 211);   r.put("FMS", 1027);   r.put("GOR", 170);  r.put("RSC", 219);
        r.put("CSD", 226);   r.put("LBJ", 231);
        return r;
    }
}