import java.util.*;
import java.io.*;

interface Color{

		default void showColor(String c){
			
			System.out.print("\n\nColor of the rectangle is : "+ c );
		}
		abstract double doubleArea();
		abstract double doublePerimeter();
}

class Rectangle implements Color ,Serializable{

		private double length;
		private double width;
		String color;


		public double getLength(){

       			return length;
        }
        public double getWidth(){

        		return width;
   		}
   		public void setLength(double l){
        
        		length = l;
    	} 
    	public void setWidth(double w){
        		
        		width = w;
		}
		public void setColor(String c){
        		
        		color = c;
		}
		public void getRectangle(){
        	    

        		System.out.print("\n\nRectangle Length = " + length);

        		System.out.print("\n\nRectangle Width = " + width);

        		
    	}
   	    public double doubleArea(){
        
        		return length * width;
   	    }
   	     public double doublePerimeter(){
        
        		return 2*(length + width);
   	    }			

}

public class Test{

		public static void main(String [] a){

				Scanner sc = new Scanner(System.in);
				int n = 0 ;
				System.out.print("\nNo. of rectangles to create : \t");
				n = sc.nextInt();

				Rectangle ob[] = new Rectangle[n];
				for (int i = 0; i<n ; i++ ) {

						double l;
						double w;
						String c;
						ob[i] = new Rectangle();
						
						try{
							System.out.print("\n\nEnter length of "+(i+1)+" Rectangle :\t");
							l = sc.nextDouble();
							System.out.print("\nEnter width of "+(i+1)+" Rectangle :\t");
							w = sc.nextDouble();
							System.out.print("\nEnter color of "+(i+1)+" Rectangle :\t");
							c = sc.next();
						}
						catch(InputMismatchException e){
							System.out.print("\nEnter correct input type \n");
							System.out.print("\nEnter length of "+(i+1)+" Rectangle :\t");
							l = sc.nextDouble();
							System.out.print("\nEnter width of "+(i+1)+" Rectangle :\t");
							w = sc.nextDouble();
							System.out.print("\nEnter color of "+(i+1)+" Rectangle :\t");
							c = sc.next();
						}
						ob[i].setLength(l);
						ob[i].setWidth(w);
						ob[i].setColor(c);
						
				}
				try{
						saveRecord(ob);
						showRecord(ob);
				}
				catch(IOException e){
						System.out.print("Fatal error");
				}
		}

		static void saveRecord(Rectangle[] ob) throws IOException{

						FileOutputStream fout = null;
						ObjectOutputStream oos = null;
						ArrayList<Rectangle> oblst =  new ArrayList<Rectangle>();
						try{
							    fout = new FileOutputStream("details.txt");
							    oos = new ObjectOutputStream(fout);
							    for (Rectangle r : ob ) {

									double area = r.doubleArea();
							   		if(area>100){
										oblst.add(r);
								    }
							     }
							    oos.writeObject(oblst);
							    System.out.print("\nFile write succesfull");

						}

						catch(IOException e){

								System.out.print("\nFile  write failed");
						}
						finally{

								if(oos != null)
									oos.close();
								if(fout !=null)
									fout.close();
						}
		}
		static void showRecord(Rectangle[] ob) throws IOException{

						FileInputStream fin = null;
						ObjectInputStream ois = null;
						ArrayList<Rectangle> oblst = null;
						try{

								fin = new FileInputStream("details.txt");
								ois = new ObjectInputStream(fin);
								oblst = (ArrayList<Rectangle>)ois.readObject();
								System.out.print("\n\nObject read succesfull ");
						}
						catch(IOException e){

								System.out.print("object reading failed");
						}
						catch(ClassNotFoundException e){

								System.out.print("Class not found");
						}
						finally{

								if(ois != null)
									ois.close();
								if(fin !=null)
									fin.close();
						}
						System.out.print("All rectangles :  \n");
						int i=0;
						for (Rectangle r : ob  ) {
								
								System.out.print("\n\n------Details of rectangle "+(i+1)+"-------");
								r.getRectangle();
								r.showColor(r.color);
								System.out.print("\n\nArea of rectangle = " + r.doubleArea());
								System.out.print("\n\nperimeter of rectangle = " + r.doublePerimeter());
								++i;
						}
						System.out.println("\n\n");


		}
}