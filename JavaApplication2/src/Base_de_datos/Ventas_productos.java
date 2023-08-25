/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base_de_datos;
//Biblioteca org.json es para manipular objetos JSON. 
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.net.*;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pilar
 */
public class Ventas_productos extends javax.swing.JFrame {
DefaultTableModel tabla2;
   int id_venta=0;
   int total =0;
   String fecha="";
   int cantidadi=0;
   ArrayList<String> productos =new ArrayList<>();
   ArrayList<String> precios =new ArrayList<>();
   ArrayList<String> cantidad =new ArrayList<>();
   
    public Ventas_productos() {
        initComponents();
        tabla2 = new DefaultTableModel();//para establecer un modelo de tabla
        tabla2.addColumn("id_venta");//para definir las columnas de tabla
        tabla2.addColumn("id_producto");
        tabla2.addColumn("fecha_venta");
        tabla2.addColumn("cantidad_producto");
        tabla2.addColumn("precio");
        tabla2.addColumn("nombre_producto");
        this.jTable1.setModel(tabla2);
        buscar_tabla("http://localhost/Appi/btn/venta_buscar.php");
        tftotal.setText(""+total);
        
    }
    public void buscar_id(){
        int columnaId = 0; 
        int numeroMaximo = 1100;
        for (int fila = 0; fila < tabla2.getRowCount(); fila++) {
                int valorActual = (int) tabla2.getValueAt(fila, columnaId);
                if (valorActual > numeroMaximo) {
                     numeroMaximo = valorActual;
                }
        }
        if (numeroMaximo==0){
            numeroMaximo=100;
        }
        int nuevoNumero = numeroMaximo + 1;
        tfidventa.setText(String.valueOf(nuevoNumero));
    }
    public void buscar_tabla(String x){//para que el codigo busque los datos y los ingrese en la tabla
        try {
            // URL del API
            URL url = new URL(x);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            int responseCode = conn.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader leer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder respuesta = new StringBuilder();
                
                while ((inputLine = leer.readLine()) != null) {
                    respuesta.append(inputLine);
                }
                leer.close();
                tabla2.setRowCount(0);
                JSONArray jsonArray = new JSONArray(respuesta.toString());
                
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id_venta = jsonObject.getInt("id_venta");
                    int id_producto = jsonObject.getInt("id_producto");
                    String fecha_venta = jsonObject.getString("fecha_venta");
                    int cantidad_producto = jsonObject.getInt("cantidad_producto");
                    int precio = jsonObject.getInt("precio");
                    String nombre_producto = jsonObject.getString("nombre_producto");
                   
                    
                    tabla2.addRow(new Object[]{id_venta, id_producto, fecha_venta, cantidad_producto, precio, nombre_producto});
                }
            }
            
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
        
    }
    //La función entrar toma una cadena acceso como parámetro y ajusta el estado de los botones en función de la operación de acceso especificada. 
    //ejemplo: Si acceso es igual a "insertar", deshabilita los botones de actualizar, borrar y buscar. y asi con los siguentes
    public void entrar (String acceso){
        if (acceso.equals("insertar")){
            btnActualizar.setEnabled(false);
             btnBorrar.setEnabled(false);
              btnBuscar.setEnabled(false);
              buscar_id();
               SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaActual = new Date();
        String fechaFormateada = dateFormat.format(fechaActual);

        tffecha.setText( fechaFormateada);
        tftotal.setEnabled(false);
        tffecha.setEnabled(false);
        tfprecio.setEnabled(false);
        tfnombrepro.setEnabled(false);
        tfidventa.setEnabled(false);
            
        }else if (acceso.equals("borrar")){
            btnActualizar.setEnabled(false);
             btnInsertar.setEnabled(false);
              btnBuscar.setEnabled(false);
              btnBorrar1.setEnabled(false);
              jButton2.setEnabled(false);
            
        }else if (acceso.equals("actualizar")){
            btnInsertar.setEnabled(false);
             btnBorrar.setEnabled(false);
              btnBuscar.setEnabled(false);
              btnBorrar1.setEnabled(false);
              jButton2.setEnabled(false);
            
        }else if (acceso.equals("buscar")){
            btnActualizar.setEnabled(false);
             btnBorrar.setEnabled(false);
              btnInsertar.setEnabled(false);
              btnBorrar1.setEnabled(false);
              jButton2.setEnabled(false);
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfidprod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfidventa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tffecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfcanpro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfnombrepro = new javax.swing.JTextField();
        tfprecio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnBorrar1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tftotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        regresar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida.png"))); // NOI18N
        regresar.setText("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });
        getContentPane().add(regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 130, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("id_producto");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 100, 20));

        tfidprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfidprodMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfidprodMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfidprodMouseReleased(evt);
            }
        });
        tfidprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfidprodActionPerformed(evt);
            }
        });
        getContentPane().add(tfidprod, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 200, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("id_venta");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 100, 20));

        tfidventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfidventaActionPerformed(evt);
            }
        });
        getContentPane().add(tfidventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 200, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("fecha_venta");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 100, 20));

        tffecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tffechaActionPerformed(evt);
            }
        });
        getContentPane().add(tffecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 200, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("cantidad_productos");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 170, 20));

        tfcanpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfcanproActionPerformed(evt);
            }
        });
        getContentPane().add(tfcanpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 200, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("nombre_producto");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 160, 20));

        tfnombrepro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfnombreproActionPerformed(evt);
            }
        });
        getContentPane().add(tfnombrepro, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 200, 30));

        tfprecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfprecioActionPerformed(evt);
            }
        });
        getContentPane().add(tfprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 200, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("precio");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 100, 20));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/informacion.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 110, -1));

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar-flecha.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 110, -1));

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 110, -1));

        btnInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/embalaje.png"))); // NOI18N
        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });
        getContentPane().add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 110, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 650, 290));

        btnBorrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnBorrar1.setText(" Borrar producto");
        btnBorrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrar1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnBorrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 150, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/terminal-de-pago.png"))); // NOI18N
        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 110, -1));

        tftotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tftotalActionPerformed(evt);
            }
        });
        getContentPane().add(tftotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 90, -1));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("TOTAL");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 60, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/windows22 (1).jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        //para tener el control de ventanas para mostrar y ocultar conforme se le balla pidiendo
        menu entrar = new menu();
        entrar.setVisible(true);//crea una instancia de la clase menu y la hace visible utilizando el método 
        this.setVisible(false);//donde se cierra la ventana actual y se muestra la nueva instancia del menu
    }//GEN-LAST:event_regresarActionPerformed

    private void tfidprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfidprodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfidprodActionPerformed

    private void tfidventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfidventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfidventaActionPerformed

    private void tffechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tffechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tffechaActionPerformed

    private void tfcanproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfcanproActionPerformed

    }//GEN-LAST:event_tfcanproActionPerformed

    private void tfnombreproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfnombreproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfnombreproActionPerformed

    private void tfprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfprecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfprecioActionPerformed

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
    //existe una clase llamada metodos que contiene métodos y lógica relacionada con la aplicación.
        Metodos medo=new Metodos();
        int idv=Integer.parseInt(tfidventa.getText());
        String idp=tfidprod.getText();
        String fe=tffecha.getText();
        String cap=tfcanpro.getText();
        String pre=tfprecio.getText();
        String nopro=tfnombrepro.getText();
        
        int num1 =Integer.parseInt(pre);
        int num2= Integer.parseInt(cap);
        if (cantidadi>=num2) {
            total +=num1*num2;
            tftotal.setText(""+total);
            num2 =num2*-1;
            id_venta=idv;
            fecha=fe;
            productos.add(nopro);
            precios.add(pre);
            cantidad.add(cap);
            tfidprod.setText("");
            tfnombrepro.setText("");
            tfprecio.setText("");
            tfcanpro.setText("");
              String parametros="fechcompra=" + fe + "&numfactura=" + idv + "&id=" + idp + "&cantidad=" + cap;
                    medo.insertar("http://localhost/Appi/btn/actualizacion_insertar.php",parametros);
            medo.actualizar("http://localhost/Appi/btn/productos_actualizar.php?id="+idp+"&cantidad="+num2);
            medo.insertar("http://localhost/Appi/btn/venta_insertar.php", "id_venta="+idv+"&id_producto="+idp+"&fecha_venta="+fe+"&cantidad_producto="+cap+"&precio="+pre+"&nombre_producto="+nopro);
            JOptionPane.showMessageDialog(this,"La venta_productos fue registrada correctamente");
        }else{
           JOptionPane.showMessageDialog(this,"lamentablemente la cantidad que deseas comprar supera nuestra disponibilidad actual en el almacén. ¿Podemos discutir alternativas? ¡Gracias!");
        }
        buscar_tabla("http://localhost/Appi/btn/venta_buscar.php?id_venta="+idv);
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    //para buscar el producto en la base de datos
        Metodos medo=new Metodos();
        String idv=tfidventa.getText();
        String idp=tfidprod.getText();
        String fe=tffecha.getText();
        String cap=tfcanpro.getText();
        String pre=tfprecio.getText();
        String nopro=tfnombrepro.getText();
        
        try {
            String leer=medo.gett("http://localhost/Appi/btn/venta_buscar.php?id_venta=" +idv+"");
            if (leer != null) {
                JSONArray J= new JSONArray(leer);

                if (J.length() > 0) {
                    buscar_tabla("http://localhost/Appi/btn/venta_buscar.php?id_venta=" +idv+"");
                } else {
                     JOptionPane.showMessageDialog(this,"La venta no existente");

                }
            }
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(this,"error");
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
    //borrar los datos de la base de datos
        Metodos medo=new Metodos();
        String idv=tfidventa.getText();
        String idp=tfidprod.getText();
        String fe=tffecha.getText();
        String cap=tfcanpro.getText();
        String pre=tfprecio.getText();
        String nopro=tfnombrepro.getText();
       
        try {
            String leer=medo.gett("http://localhost/Appi/btn/venta_buscar.php?id_venta="+idv+"");
            if (leer != null) {
                JSONArray J= new JSONArray(leer);

                if (J.length() > 0) {
                     JOptionPane.showMessageDialog(this,"La venta se borro");
                    medo.borrar("http://localhost/Appi/btn/venta_borrar.php?id_venta="+idv+"");
                } else {

                    JOptionPane.showMessageDialog(this,"La venta no existe");

                }
            }
         buscar_tabla("http://localhost/Appi/btn/venta_buscar.php?");
        } catch (IOException ex) {
            System.out.println("error");
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    //la actualizacion de cuaqluier dato ya creada en la base de datos
        Metodos medo=new Metodos();
        String idv=tfidventa.getText();
        String idp=tfidprod.getText();
        String fe=tffecha.getText();
        String cap=tfcanpro.getText();
        String pre=tfprecio.getText();
        String nopro=tfnombrepro.getText();
        
        try {
            String leer=medo.gett("http://localhost/Appi/btn/venta_buscar.php?id_venta="+idv+"");
            if (leer != null) {
                JSONArray J= new JSONArray(leer);

                if (J.length() > 0) {
                    JOptionPane.showMessageDialog(this,"La venta se actualizo correctamente");
                    medo.actualizar("http://localhost/Appi/btn/venta_actualizar.php?id_venta="+idv+"&id_producto="+idp+"&fecha_venta="+fe+"&cantidad_producto="+cap+"&precio="+pre+"&nombre_producto="+nopro);
                    
                }else
                {
                    JOptionPane.showMessageDialog(this,"La venta no existe");
                }
            }

        } catch (IOException ex) {
            System.out.println("error");
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tftotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftotalActionPerformed
    
    }//GEN-LAST:event_tftotalActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       total=0;
       tftotal.setText(""+total);
        try {
       File file = new File("C:/Users/Aaron/Documents/Examenes de liz/ticket "+id_venta+".pdf");
        file.getParentFile().mkdirs();
        
        PdfWriter writer;
      
            writer = new PdfWriter(file);
       PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A6);
        document.setMargins(10, 10, 10, 10);
        PdfFont font = PdfFontFactory.createFont();
            float customFontSize = 17;
            Paragraph lineas = new Paragraph("*****************************************").setFontSize(customFontSize);;
        Paragraph header = new Paragraph("Ticket de venta").setTextAlignment(TextAlignment.RIGHT).setFontSize(customFontSize);
                
                document.add(header);
                document.add(lineas);
                Paragraph info = new Paragraph("numero de venta: "+ id_venta + "\n fecha : "+fecha).setTextAlignment(TextAlignment.RIGHT).setFontSize(customFontSize);
                document.add(info);
                document.add(lineas);
                for (int i = 0; i < productos.size(); i++) {
                    
                    document.add(new Paragraph(" "+productos.get(i)+"...... $ "+precios.get(i)+" X "+cantidad.get(i)).setTextAlignment(TextAlignment.RIGHT).setFontSize(customFontSize));
                }

double total = 0.0;
for (int i = 0; i < productos.size(); i++) {
           double num1 =Integer.parseInt(precios.get(i));
            double num2 =Integer.parseInt(cantidad.get(i));
    double subtotal =num1*num2;
    total += subtotal;
}
        document.add(lineas);
        Paragraph totalInfo = new Paragraph("Total: $ " + total).setTextAlignment(TextAlignment.RIGHT).setFontSize(customFontSize);
            document.add(totalInfo);
        document.close();
        JOptionPane.showMessageDialog(this, "Gracias por su compra, Vuelva pronto");
        buscar_id();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
    }
        String filepad="C:/Users/Aaron/Documents//Examenes de liz/ticket "+id_venta+".pdf";
       File pdfFile = Paths.get(filepad).toFile();
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.PRINT)) {
                try {
                    desktop.print(pdfFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this,"Error de impresión");
            }
        } else {
            JOptionPane.showMessageDialog(this,"Sistema no disponible");
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tfidprodMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfidprodMouseExited
        Metodos medo=new Metodos(); 
        String id=tfidprod.getText();
        String leer;
        String leer2;
        
        try {
            leer2 = medo.gett("http://localhost/Appi/btn/productos_buscar.php?id="+id+"");
            leer = medo.gett("http://localhost/Appi/btn/registro_buscar.php?id="+id);
        
        JSONArray j= new JSONArray(leer); if (j.length() > 0){
                                   for (int i = 0; i < j.length(); i++) {
                    JSONObject jsonObject = j.getJSONObject(i);
                    String nombre2 = jsonObject.getString("nombre"
                            + "");
                    int prec = jsonObject.getInt("precio");
                    String pres = jsonObject.getString("presentacion");
                                    tfprecio.setText(""+prec);
                                    tfnombrepro.setText(nombre2);
                                    }
                
            }else if (tfidprod.getText() != ""){ System.out.println("producto no existente");}
                                 
        JSONArray j2= new JSONArray(leer2); if (j.length() > 0) {
                                   for (int i = 0; i < j2.length(); i++) {
                    JSONObject jsonObject = j2.getJSONObject(i);
                    cantidadi = jsonObject.getInt("cantidad");
                                    }
                                 }
        } catch (IOException ex) {
            Logger.getLogger(Ventas_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tfidprodMouseExited

    private void tfidprodMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfidprodMousePressed

    }//GEN-LAST:event_tfidprodMousePressed

    private void tfidprodMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfidprodMouseReleased
    
    }//GEN-LAST:event_tfidprodMouseReleased

    private void btnBorrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrar1ActionPerformed
        String prod=tfnombrepro.getText();
        Metodos medo =new Metodos();
        String idp=tfidprod.getText();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).equals(prod)) {
                productos.remove(i);
                precios.remove(i);
                medo.actualizar("http://localhost/Appi/btn/productos_actualizar.php?id="+idp+"&cantidad="+cantidad.get(i));
            
                cantidad.remove(i);
                break; 
            }}
        int columnaABuscar = 5;
int filaAEliminar = -1;

for (int fila = 0; fila < tabla2.getRowCount(); fila++) {
    String valorActual = (String) tabla2.getValueAt(fila, columnaABuscar);
    if (valorActual.equals(prod)) {
        filaAEliminar = fila;
        break; // Stop the loop after finding the value
    }
}

if (filaAEliminar != -1) {
   
tabla2.removeRow(filaAEliminar);

}
            
    }//GEN-LAST:event_btnBorrar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventas_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventas_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventas_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventas_productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBorrar1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton regresar;
    private javax.swing.JTextField tfcanpro;
    private javax.swing.JTextField tffecha;
    private javax.swing.JTextField tfidprod;
    private javax.swing.JTextField tfidventa;
    private javax.swing.JTextField tfnombrepro;
    private javax.swing.JTextField tfprecio;
    private javax.swing.JTextField tftotal;
    // End of variables declaration//GEN-END:variables
}
