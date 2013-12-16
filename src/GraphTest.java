/******************************************************************************
 *
 * Test Class for the Graph Class allows the user to:
 *  - enter a new course   (the course name is provided by the user)
 *  - enter a prerequisite relationship between two courses (two course names are provided by user)
 *      (error message is given if both courses do not exist)
 *  - remove a prerequisite relationship between two courses  (course names provided by user)
 *  - report if one course is the immediate prerequisite of another (course names provided by user)
 *  - report if there is a prerequisite path between on course and another (course names provided by user)
 *  - output all courses which can be taken directly after taking that course (course name provided by user)
 *  - outputs all courses which have been entered

 *
 * @see
 *   <A HREF="https://github.com/AugustBrenner">
 *       Checkout my GitHub</A>
 *
 * @author
 * August Brenner
 * G00682282
 *
 * @version
 *   December 18th, 2013
 ******************************************************************************/
import java.util.*;
public class GraphTest {
    public static void main(String [] args){
        // Instantiate scanner object
        Scanner input = new Scanner(System.in);

        // instantiate Graph() class
        System.out.println("Please enter the number of classes in your department");
        Graph graph = new Graph(input.nextInt());

        // do while loop to make test selections
        int userSelection;
        int position1;
        int position2;
        String output;
        int[] prerequisites;
        do{
            Object[] labels = graph.getAllLabels();
            for(int i = 0; i < labels.length; i++){
                if(labels[i] == null){
                    System.out.printf("%d)\n",i);
                } else{
                    System.out.printf("%d) %s\n", i, labels[i].toString());
                }
            }
            System.out.println();

            // prompt user for inputs for actions testing path class
            System.out.println("Please select from one of the following menu options:\n0)Enter Course\n" +
                    "1)Enter Prerequisite\n2)Remove Prerequisite\n3)Report if Prerequisite Relationship exists\n" +
                    "4)Report if Prerequisite Path exists\n5)Output all Courses available After Course\n6)Exit");
            userSelection = input.nextInt();


            // switch for handling selections testing all parts of the graph class
            switch(userSelection){
                // Case 0 prompts user to add a course
                case 0:
                    System.out.println("Please Select a Course position:");
                    position1 = input.nextInt();
                    input.nextLine();
                    System.out.println("Please enter the name of a course:");
                    graph.setLabel(position1, input.nextLine());
                    break;
                // Case 1 Prompts user enter a prerequisite edge for the course
                case 1:
                    System.out.println("Please Select a Course position to set a Prerequisite for it:");
                    position1 = input.nextInt();
                    System.out.println("Please Select a Course position to set as a Prerequisite");
                    graph.addEdge(input.nextInt(), position1);
                    break;
                // Case 2 deletes the prerequisite condition between two vertexes
                case 2:
                    System.out.println("Please Select a Course position to remove a Prerequisite for it:");
                    position1 = input.nextInt();
                    System.out.println("Please Select a Course position to remove as a Prerequisite");
                    graph.removeEdge(input.nextInt(), position1);
                    break;
                // Case 3 reports if two vertexes have an edge connecting them
                case 3:
                    System.out.println("Please Select a Course position to see if a Prerequisite for it exists:");
                    position1 = input.nextInt();
                    System.out.println("Please Select a Course position to see if it is a Prerequisite");
                    position2 = input.nextInt();

                    if (graph.getLabel(position2) == null){
                        output = String.format("%d", position2);
                    } else {
                        output = graph.getLabel(position2).toString();
                    }
                    if(graph.isEdge(position2, position1)){
                        System.out.printf("%s is a Prerequisite\n", output);
                    } else{
                        System.out.printf("%s is NOT a Prerequisite\n", output);
                    }
                    break;
                // Case 4 Reports if a prerequisite path exists for two vertexes
                case 4:
                    System.out.println("Please Select a Course position to see if a Prerequisite path for it exists:");
                    position1 = input.nextInt();
                    System.out.println("Please Select a Course position to see if it is on the Prerequisite path");
                    position2 = input.nextInt();

                    if (graph.getLabel(position2) == null){
                        output = String.format("%d", position2);
                    } else {
                        output = graph.getLabel(position2).toString();
                    }
                    if(graph.isPath(position2, position1)){
                        System.out.printf("%s has a Prerequisite path\n", output);
                    } else{
                        System.out.printf("%s does NOT have a Prerequisite\n", output);
                    }
                    break;
                // Case 5 outputs all available courses after
                case 5:
                    System.out.println("Please Select a Course position to see all course you can take after:");
                    position1 = input.nextInt();
                    prerequisites = graph.neighbors(position1);
                    System.out.println("You can take the following courses:");
                        for(int prerequisite : prerequisites){
                            if (graph.getLabel(prerequisite) == null){
                                output = String.format("%d", prerequisite);
                            } else {
                                output = graph.getLabel(prerequisite).toString();
                            }
                            System.out.println(output);
                        }
                    break;
            }

        } while(userSelection != 6);

    }
}
