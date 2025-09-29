/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package citasss.paneles;

import citasss.beans.PersonaBean;
import citasss.gestores.GestionPersonaBD;
import citasss.gestores.GestionTrabajadorasBD;
import citasss.utils.FechasUtils;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vPalomo
 */
public class ListaPersonasPanel extends javax.swing.JPanel {

    private String idCita;
    private String idTrabajadora;
    private String modo;
    public static String CONSULTA = "Consulta";
    public static String CONSULTA_ALTA = "ConsultaConAlta";
    public static String ALTA_CITA = "AltaCita";

    /**
     * Abre la lista de personas. Si el modo es AltaCita hay que enviar los datos de idCita e idTrabajadora para que se pueda asignar luego a la persona que se eliga a la cita y 
     * a la trabajadora en cuestion. también se abrirá luego la lista de posibles demandas que se pueden solicitar.
     * Si el modo es consulta no hace falta enviar ni la cita ni la trabajadora. Si es consulta con alta el botón de borrar estará activo
     * @param idCita
     * @param idTrabajadora
     * @param modo 
     */
    public ListaPersonasPanel(String idCita, String idTrabajadora, String modo) {
        this.idCita = idCita;
        this.idTrabajadora = idTrabajadora;
        this.modo = modo;
        initComponents();
        cargarTabla(Integer.parseInt(idTrabajadora), jCheckHist.isSelected() ? 0 : 1);
        ponListenerTablaCitas();
        selectCombo(idTrabajadora);
        System.out.println("Modo: "+modo);
        if(modo.equalsIgnoreCase(CONSULTA)){
            jButtonBorrar.setEnabled(false);
        }
    }

    private void ponListenerTablaCitas() {
        JPanel padre = this;
        jTablePersonas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String idPersona = (String) jTablePersonas.getValueAt(jTablePersonas.getSelectedRow(), 1);
                    System.out.println("Se ha hecho un click");
                }

                if (e.getClickCount() == 2) {
                    if (modo.equalsIgnoreCase(ALTA_CITA)) {
                        String idPersona = (String) jTablePersonas.getValueAt(jTablePersonas.getSelectedRow(), 0);
                        JDialog frame = new JDialog(new JFrame(), "Seleccion de servicio", true);
                        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                        frame.getContentPane().add(new ServiciosPanel(idCita, idTrabajadora, idPersona));
                        frame.pack();
                        frame.setLocationRelativeTo(padre);
                        frame.setVisible(true);
                        Window w = SwingUtilities.getWindowAncestor(padre);
                        w.setVisible(false);
                    }else if(modo.equalsIgnoreCase(CONSULTA)){
                        String idPersona = (String) jTablePersonas.getValueAt(jTablePersonas.getSelectedRow(), 0);
                        PersonaBean persona=new PersonaBean(idPersona);
                        persona.cargarDatos();
                        JDialog frame = new JDialog(new JFrame(),"Consulta", true);
                        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                        frame.getContentPane().add(new MttoPersonaPanel(MttoPersonaPanel.CONSULTA, persona));
                        frame.pack();
                        frame.setLocationRelativeTo(padre);
                        frame.setVisible(true);
                    }else if(modo.equalsIgnoreCase(CONSULTA_ALTA)){
                        String idPersona = (String) jTablePersonas.getValueAt(jTablePersonas.getSelectedRow(), 0);
                        PersonaBean persona=new PersonaBean(idPersona);
                        persona.cargarDatos();
                        JDialog frame = new JDialog(new JFrame(),"Consulta", true);
                        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                        frame.getContentPane().add(new MttoPersonaPanel(MttoPersonaPanel.MTTO, persona));
                        frame.pack();
                        frame.setLocationRelativeTo(padre);
                        frame.setVisible(true);
                    }
                    cargarTabla(Integer.parseInt(idTrabajadora), jCheckHist.isSelected() ? 0 : 1);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePersonas = new javax.swing.JTable();
        jTextFiltro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jCheckHist = new javax.swing.JCheckBox();
        jComboTrabajadora = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButtonBorrar = new javax.swing.JButton();

        jTablePersonas.setAutoCreateRowSorter(true);
        jTablePersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nombre", "Apellidos", "DNI", "Fecha Nac.", "Trabajadora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablePersonas);
        if (jTablePersonas.getColumnModel().getColumnCount() > 0) {
            jTablePersonas.getColumnModel().getColumn(0).setResizable(false);
            jTablePersonas.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTablePersonas.getColumnModel().getColumn(1).setResizable(false);
            jTablePersonas.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTablePersonas.getColumnModel().getColumn(2).setResizable(false);
            jTablePersonas.getColumnModel().getColumn(2).setPreferredWidth(300);
            jTablePersonas.getColumnModel().getColumn(3).setResizable(false);
            jTablePersonas.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTablePersonas.getColumnModel().getColumn(4).setResizable(false);
            jTablePersonas.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTablePersonas.getColumnModel().getColumn(5).setResizable(false);
            jTablePersonas.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        jTextFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFiltroActionPerformed(evt);
            }
        });

        jLabel1.setText("Filtro");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Aceptar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jCheckHist.setText("Histórico");
        jCheckHist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckHistActionPerformed(evt);
            }
        });

        jComboTrabajadora.setModel(GestionTrabajadorasBD.getModeloComboTrabajadoras0());
        jComboTrabajadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboTrabajadoraActionPerformed(evt);
            }
        });

        jLabel2.setText("Trabajadora");

        jButtonBorrar.setText("Borrar persona");
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboTrabajadora, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckHist)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jCheckHist)
                    .addComponent(jComboTrabajadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButtonBorrar))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFiltroActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int filaSeleccionada = jTablePersonas.getSelectedRow();
        if (filaSeleccionada != -1) { // -1 significa que no hay fila seleccionada
            JPanel padre = this;
            String idPersona = (String) jTablePersonas.getValueAt(jTablePersonas.getSelectedRow(), 0);
            JDialog frame = new JDialog(new JFrame(), "Seleccion de servicio", true);
            frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
            frame.getContentPane().add(new ServiciosPanel(idCita, idTrabajadora, idPersona));
            frame.pack();
            frame.setLocationRelativeTo(padre);
            frame.setVisible(true);
            //cargarCitasFecha(jTextFecha1.getText());
//                    System.out.println("Se ha hecho doble click");
//                    String idPersona =(String) jTablePersonas.getValueAt(jTablePersonas.getSelectedRow(), 0);
//                    //String id = (String) jTablePersonas.getModel().getValueAt(jTablePersonas.getSelectedRow(), 1);
//                    System.out.println("Id: " + idPersona);
//                    System.out.println("Result: "+GestionCitasBD.asignarCitaDisponible(idCita,idPersona, idTrabajadora));
            Window w = SwingUtilities.getWindowAncestor(padre);
            w.setVisible(false);
        } else {
            System.out.println("Sin seleccion");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Window w = SwingUtilities.getWindowAncestor(this);
        w.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckHistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckHistActionPerformed
        String idTrabajadora = jComboTrabajadora.getModel().getElementAt(jComboTrabajadora.getSelectedIndex()).split(" - ")[0];
        int id = Integer.parseInt(idTrabajadora);
        cargarTabla(id, jCheckHist.isSelected() ? 0 : 1);
    }//GEN-LAST:event_jCheckHistActionPerformed

    private void jComboTrabajadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboTrabajadoraActionPerformed
        String idTrabajadora = jComboTrabajadora.getModel().getElementAt(jComboTrabajadora.getSelectedIndex()).split(" - ")[0];
        int id = Integer.parseInt(idTrabajadora);
        cargarTabla(id, jCheckHist.isSelected() ? 0 : 1);
    }//GEN-LAST:event_jComboTrabajadoraActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarTablaFiltro(jTextFiltro.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        int filaSeleccionada = jTablePersonas.getSelectedRow();
        String idPersona = (String) jTablePersonas.getValueAt(filaSeleccionada, 0);
        int r = GestionPersonaBD.borrarPersona(idPersona);
        if (r == 1) {
            JOptionPane.showMessageDialog(null, "Persona borrada (" + idPersona + ")", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        cargarTablaFiltro(jTextFiltro.getText());
    }//GEN-LAST:event_jButtonBorrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JCheckBox jCheckHist;
    private javax.swing.JComboBox<String> jComboTrabajadora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePersonas;
    private javax.swing.JTextField jTextFiltro;
    // End of variables declaration//GEN-END:variables

    private void cargarTabla(int idTrabajadora, int activos) {
        ArrayList<PersonaBean> listaPersonas = GestionPersonaBD.getListaPersonas(activos, idTrabajadora);
        DefaultTableModel datosTabla = (DefaultTableModel) jTablePersonas.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            datosTabla.removeRow(i - 1);
        }
        for (PersonaBean persona : listaPersonas) {
            datosTabla.addRow(new Object[]{
                persona.getIdPersona(),
                persona.getNombre(),
                persona.getApellidos(),
                persona.getDNI(),
                FechasUtils.fecha(persona.getFechaNac(), "/"),
                GestionTrabajadorasBD.getNombreTrabajadora(persona.getIdTrabajadora())
            });
        }
    }

    private void selectCombo(String idTrabajadora) {
        System.out.println("selectCombo: " + idTrabajadora);
        for (int i = 0; i < jComboTrabajadora.getModel().getSize(); i++) {
            String aux = jComboTrabajadora.getModel().getElementAt(i).split(" - ")[0];
            if (idTrabajadora.equalsIgnoreCase(aux)) {
                jComboTrabajadora.setSelectedIndex(i);
            }
        }

    }

    private void cargarTablaFiltro(String filtro) {
        String idTrabajadora = jComboTrabajadora.getModel().getElementAt(jComboTrabajadora.getSelectedIndex()).split(" - ")[0];
        int id = Integer.parseInt(idTrabajadora);
        ArrayList<PersonaBean> listaPersonas = GestionPersonaBD.getListaPersonasFiltro(jCheckHist.isSelected() ? 0 : 1, id, filtro);
        DefaultTableModel datosTabla = (DefaultTableModel) jTablePersonas.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            datosTabla.removeRow(i - 1);
        }
        for (PersonaBean persona : listaPersonas) {
            datosTabla.addRow(new Object[]{
                persona.getIdPersona(),
                persona.getNombre(),
                persona.getApellidos(),
                persona.getDNI(),
                FechasUtils.fecha(persona.getFechaNac(), "/")
            });
        }
    }
}
