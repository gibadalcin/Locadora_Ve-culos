package dtos;


public class DTOComboBox {
   private int id;
   private String description;
   
   public DTOComboBox(int id, String description) {
	   super();
	   this.id = id;
	   this.description = description;
   }
   
   public int getId() {
	   return id;
   }
   public void setId(int id) {
	   this.id = id;
   }
   public String getDescription() {
	   return description;
   }
   public void setDescription(String description) {
	   this.description = description;
   }
   
   @Override
   public String toString() {
	   return description;
   }
   
   @Override
   public boolean equals(Object dto) {
	   return this.description.equals(((DTOComboBox)dto).description);
   }
}
