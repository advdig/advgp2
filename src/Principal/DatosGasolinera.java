/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import conexion.ConnectionTableDB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Advantech Digital
 */
public class DatosGasolinera extends javax.swing.JFrame {

    ConnectionTableDB conn;
    private File file;
    private FileInputStream stream;
    private String USER, PASSWORD, DB, consulta, eds;
    private boolean fileChanged = false;

    /**
     * Creates new form DatosGasolinera
     */
    public DatosGasolinera(String usuario, String password) {
        USER = usuario;
        PASSWORD = password;
        DB = "adv_facturacion";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("eds.adv"));
            eds = br.readLine();
            consulta = "SELECT razon_social, ruc, direccion, email_estacion, despachadores_turno, tipo_ambiente, "
                    + "obligado_llevar_contabilidad, nombre_comercial, contribuyente_especial, tipo_cierre_turnos, "
                    + "AES_DECRYPT(contraseña_mail, 'thekey') "
                    + "FROM datos_gasolinera";
            conn = new ConnectionTableDB(USER, PASSWORD, DB, consulta, false);
            initComponents();
            setSize(1000, 500);
            txtEDS.setText(eds.toUpperCase());
            if (conn.getRowCount() == 0) {
                btnGuardar.setText("Guardar");
            } else {
                btnGuardar.setText("Editar");
                txtRazonSocial.setText((String) conn.getValueAt(0, 0));
                txtRUC.setText((String) conn.getValueAt(0, 1));
                txtDireccion.setText((String) conn.getValueAt(0, 2));
                txtPropietario.setText((String) conn.getValueAt(0, 3));
                txtDespachadores.setText(String.valueOf(conn.getValueAt(0, 4)));
                cbxAmbiente.setSelectedIndex(Integer.parseInt((String) conn.getValueAt(0, 5)) - 1);
                chkContabilidad.setSelected(((String) conn.getValueAt(0, 6)).equalsIgnoreCase("SI"));
                txtNombreComercial.setText((String) conn.getValueAt(0, 7));
                txtContribuyente.setText((String) conn.getValueAt(0, 8));
                txtCertificado.setText("Certificado en Uso");
                cbxTurno.setSelectedIndex((Integer) conn.getValueAt(0, 9) - 1);
                java.sql.ResultSet rs = conn.stSentencias.executeQuery("SELECT AES_DECRYPT(contraseña_mail, 'thekey'), "
                        + "AES_DECRYPT(contraseña_certificado, 'thekey') "
                        + "FROM datos_gasolinera");
                java.sql.Blob mail = null, certificado = null;
                if (rs.next()) {
                    mail = rs.getBlob(1);
                    certificado = rs.getBlob(2);
                }
                if (mail != null) {
                    passClaveEmail.setText(new String(mail.getBytes(1, (int) mail.length())));
                }
                if (certificado != null) {
                    passClave.setText(new String(certificado.getBytes(1, (int) certificado.length())));
                }
            }
        } catch (SQLException | IOException ex) {
            System.err.println(ex.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        lblEDS = new javax.swing.JLabel();
        txtEDS = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        lblRazonSocial = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        lblRUC = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreComercial = new javax.swing.JTextField();
        lblNombreComercial = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        chkContabilidad = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtContribuyente = new javax.swing.JTextField();
        lblContribuyente = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPropietario = new javax.swing.JTextField();
        lblPropietario = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        passClaveEmail = new javax.swing.JPasswordField();
        lblClaveEmail = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDespachadores = new javax.swing.JTextField();
        lblDespachadores = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbxAmbiente = new javax.swing.JComboBox();
        lblAmbiente = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCertificado = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        passClave = new javax.swing.JPasswordField();
        lblClave = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbxTurno = new javax.swing.JComboBox();
        lblTuno = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos de la Estación de Servicio");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        lblEDS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEDS.setText("Estacion de Servicio:");
        jPanel1.add(lblEDS);

        txtEDS.setEditable(false);
        txtEDS.setColumns(30);
        txtEDS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtEDS);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.GridLayout(0, 3, 10, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Razón Social:");
        jPanel2.add(jLabel1);

        txtRazonSocial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRazonSocial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRazonSocialFocusLost(evt);
            }
        });
        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyTyped(evt);
            }
        });
        jPanel2.add(txtRazonSocial);

        lblRazonSocial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRazonSocial.setText("*");
        jPanel2.add(lblRazonSocial);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("RUC:");
        jPanel2.add(jLabel3);

        txtRUC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRUC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRUCFocusLost(evt);
            }
        });
        txtRUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRUCKeyTyped(evt);
            }
        });
        jPanel2.add(txtRUC);

        lblRUC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRUC.setText("*");
        jPanel2.add(lblRUC);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombre Comercial:");
        jPanel2.add(jLabel5);

        txtNombreComercial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombreComercial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreComercialFocusLost(evt);
            }
        });
        jPanel2.add(txtNombreComercial);

        lblNombreComercial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNombreComercial.setText("*");
        jPanel2.add(lblNombreComercial);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Direccion:");
        jPanel2.add(jLabel7);

        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDireccionFocusLost(evt);
            }
        });
        jPanel2.add(txtDireccion);

        lblDireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDireccion.setText("*");
        jPanel2.add(lblDireccion);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Obligado a Contabilidad:");
        jPanel2.add(jLabel9);

        chkContabilidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(chkContabilidad);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(jLabel10);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Contribuyente Especial #:");
        jPanel2.add(jLabel11);

        txtContribuyente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtContribuyente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContribuyenteKeyTyped(evt);
            }
        });
        jPanel2.add(txtContribuyente);

        lblContribuyente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(lblContribuyente);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Email:");
        jPanel2.add(jLabel13);

        txtPropietario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPropietario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPropietarioFocusLost(evt);
            }
        });
        jPanel2.add(txtPropietario);

        lblPropietario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPropietario.setText("*");
        jPanel2.add(lblPropietario);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Contraseña:");
        jPanel2.add(jLabel22);

        passClaveEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passClaveEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passClaveEmailFocusLost(evt);
            }
        });
        jPanel2.add(passClaveEmail);

        lblClaveEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblClaveEmail.setText("*");
        jPanel2.add(lblClaveEmail);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("N° de Despachadores:");
        jPanel2.add(jLabel15);

        txtDespachadores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDespachadores.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDespachadoresFocusLost(evt);
            }
        });
        txtDespachadores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDespachadoresKeyTyped(evt);
            }
        });
        jPanel2.add(txtDespachadores);

        lblDespachadores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDespachadores.setText("*");
        jPanel2.add(lblDespachadores);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Ambiente:");
        jPanel2.add(jLabel17);

        cbxAmbiente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbxAmbiente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pruebas", "Producción" }));
        jPanel2.add(cbxAmbiente);

        lblAmbiente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(lblAmbiente);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Certificado:");
        jPanel2.add(jLabel19);

        txtCertificado.setEditable(false);
        txtCertificado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txtCertificado);

        btnSeleccionar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSeleccionar.setText("Seleccionar...");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        jPanel2.add(btnSeleccionar);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Clave:");
        jPanel2.add(jLabel21);

        passClave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passClave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passClaveFocusLost(evt);
            }
        });
        jPanel2.add(passClave);

        lblClave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblClave.setText("*");
        jPanel2.add(lblClave);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Tipo Cierre de Turno:");
        jPanel2.add(jLabel18);

        cbxTurno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbxTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Abierto", "Cerrado" }));
        jPanel2.add(cbxTurno);

        lblTuno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(lblTuno);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar);

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelar);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRUCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRUCFocusLost
        // TODO add your handling code here:
        if (txtRUC.getText().length() == 0) {
            lblRUC.setText("* " + "Campo Obligatorio");
            lblRUC.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_txtRUCFocusLost

    private void txtRazonSocialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRazonSocialFocusLost
        // TODO add your handling code here:
        if (txtRazonSocial.getText().length() == 0) {
            lblRazonSocial.setText("* " + "Campo Obligatorio");
            lblRazonSocial.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_txtRazonSocialFocusLost

    private void txtNombreComercialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreComercialFocusLost
        // TODO add your handling code here:
        if (txtNombreComercial.getText().length() == 0) {
            lblNombreComercial.setText("* " + "Campo Obligatorio");
            lblNombreComercial.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_txtNombreComercialFocusLost

    private void txtDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionFocusLost
        // TODO add your handling code here:
        if (txtDespachadores.getText().length() == 0) {
            lblDireccion.setText("* " + "Campo Obligatorio");
            lblDireccion.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_txtDireccionFocusLost

    private void txtDespachadoresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDespachadoresFocusLost
        // TODO add your handling code here:
        if (txtDespachadores.getText().length() == 0) {
            lblDespachadores.setText("* " + "Campo Obligatorio");
            lblDespachadores.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_txtDespachadoresFocusLost

    private void passClaveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passClaveFocusLost
        // TODO add your handling code here:
        if (new String(passClave.getPassword()).length() == 0) {
            lblClave.setText("* " + "Campo Obligatorio");
            lblClave.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_passClaveFocusLost

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo P12", "p12");
            fc.setFileFilter(filter);
            int open = fc.showOpenDialog(this);
            if (open == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                String name = file.getName();
                if (name.substring(name.lastIndexOf(".") + 1).equalsIgnoreCase("p12")) {
                    stream = new FileInputStream(file);
                    txtCertificado.setText(name.substring(0, name.length() - 4));
                    fileChanged = true;
                    txtCertificado.setForeground(java.awt.Color.BLACK);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Debe escoger un archivo P12.");
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (txtRazonSocial.getText().length() != 0 && txtRUC.getText().length() != 0
                && txtNombreComercial.getText().length() != 0
                && txtDireccion.getText().length() != 0 && txtDireccion.getText().length() != 0
                && txtPropietario.getText().length() != 0 && new String(passClaveEmail.getPassword()).length() != 0
                && new String(passClave.getPassword()).length() != 0
                && !txtCertificado.getText().equalsIgnoreCase("Certificado en uso")) {
            if (btnGuardar.getText().equalsIgnoreCase("Guardar")) {
                fileChanged = false;
            }
            try {
                conn.consulta("SELECT ruc FROM datos_gasolinera");
                if (conn.getRowCount() == 0) {
                    conn.ejecutar("INSERT INTO datos_gasolinera (razon_social, ruc, nombre_comercial, direccion, obligado_llevar_contabilidad, "
                            + "contribuyente_especial, email_estacion, contraseña_mail, despachadores_turno, tipo_ambiente, contraseña_certificado, tipo_cierre_turnos) "
                            + "VALUES ('"
                            + txtRazonSocial.getText() + "', '"
                            + txtRUC.getText() + "', '"
                            + txtNombreComercial.getText() + "', '"
                            + txtDireccion.getText() + "', '"
                            + (chkContabilidad.isSelected() ? "SI" : "NO") + "', '"
                            + (txtContribuyente.getText().length() == 0 ? "0" : txtContribuyente.getText()) + "', '"
                            + txtPropietario.getText() + "', "
                            + "AES_ENCRYPT('" + new String(passClaveEmail.getPassword()) + "', 'thekey'), "
                            + Integer.parseInt(txtDespachadores.getText()) + ", "
                            + (cbxAmbiente.getSelectedIndex() + 1) + "', '"
                            + "AES_ENCRYPT('" + new String(passClave.getPassword()) + "', 'thekey'), "
                            + (cbxTurno.getSelectedIndex() + 1) + ")");
                    conn.psPrepararSentencias = conn.coneccion.prepareStatement("INSERT INTO datos_gasolinera (certificado_digital) VALUES (?)");
                    conn.psPrepararSentencias.setBinaryStream(1, stream, (int) file.length());
                    conn.psPrepararSentencias.executeUpdate();
                    conn.psPrepararSentencias.close();
                    javax.swing.JOptionPane.showMessageDialog(this, "Datos agregados correctamente.");
                } else {
                    conn.ejecutar("UPDATE datos_gasolinera "
                            + "SET "
                            + "razon_social = '" + txtRazonSocial.getText() + "', "
                            + "ruc = '" + txtRUC.getText() + "', "
                            + "nombre_comercial = '" + txtNombreComercial.getText() + "', "
                            + "direccion = '" + txtDireccion.getText() + "', "
                            + "obligado_llevar_contabilidad = '" + (chkContabilidad.isSelected() ? "SI" : "NO") + "', "
                            + "contribuyente_especial = '" + (txtContribuyente.getText().length() == 0 ? "0" : txtContribuyente.getText()) + "', "
                            + "email_estacion = '" + txtPropietario.getText() + "', "
                            + "contraseña_mail = AES_ENCRYPT('" + new String(passClaveEmail.getPassword()) + "', 'thekey'), "
                            + "despachadores_turno = " + Integer.parseInt(txtDespachadores.getText()) + ", "
                            + "tipo_ambiente = '" + (cbxAmbiente.getSelectedIndex() + 1) + "', "
                            + "contraseña_certificado = AES_ENCRYPT('" + new String(passClave.getPassword()) + "', 'thekey'), "
                            + "tipo_cierre_turnos = " + (cbxTurno.getSelectedIndex() + 1));
                    if (fileChanged) {
                        conn.psPrepararSentencias = conn.coneccion.prepareStatement("UPDATE datos_gasolinera SET certificado_digital = ?");
                        conn.psPrepararSentencias.setBinaryStream(1, stream, (int) file.length());
                        conn.psPrepararSentencias.executeUpdate();
                        conn.psPrepararSentencias.close();
                    }
                    javax.swing.JOptionPane.showMessageDialog(this, "Datos editados correctamente.");
                }
                dispose();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos obligatorios.");
            txtCertificado.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtRUCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRUCKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || txtRUC.getText().length() >= 13) {
            evt.consume();
        }
        if (txtRUC.getText().length() <= 13) {
            lblRUC.setText("* " + "Obligado 13 dígitos");
        } else {
            lblRUC.setText("* OK");
            lblRUC.setForeground(java.awt.Color.BLACK);
        }
    }//GEN-LAST:event_txtRUCKeyTyped

    private void txtContribuyenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContribuyenteKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtContribuyenteKeyTyped

    private void txtDespachadoresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDespachadoresKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDespachadoresKeyTyped

    private void txtRazonSocialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyTyped
        // TODO add your handling code here:
        if (txtRazonSocial.getText().length() != 0) {
            lblRazonSocial.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_txtRazonSocialKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        conn.desconectar();
    }//GEN-LAST:event_formWindowClosed

    private void passClaveEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passClaveEmailFocusLost
        // TODO add your handling code here:
        if (new String(passClaveEmail.getPassword()).length() == 0) {
            lblClaveEmail.setText("* " + "Campo Obligatorio");
            lblClaveEmail.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_passClaveEmailFocusLost

    private void txtPropietarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPropietarioFocusLost
        // TODO add your handling code here:
        if (txtPropietario.getText().length() == 0) {
            lblPropietario.setText("* " + "Campo Obligatorio");
            lblPropietario.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_txtPropietarioFocusLost

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatosGasolinera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new DatosGasolinera("", "").setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox cbxAmbiente;
    private javax.swing.JComboBox cbxTurno;
    private javax.swing.JCheckBox chkContabilidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAmbiente;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblClaveEmail;
    private javax.swing.JLabel lblContribuyente;
    private javax.swing.JLabel lblDespachadores;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEDS;
    private javax.swing.JLabel lblNombreComercial;
    private javax.swing.JLabel lblPropietario;
    private javax.swing.JLabel lblRUC;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblTuno;
    private javax.swing.JPasswordField passClave;
    private javax.swing.JPasswordField passClaveEmail;
    private javax.swing.JTextField txtCertificado;
    private javax.swing.JTextField txtContribuyente;
    private javax.swing.JTextField txtDespachadores;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEDS;
    private javax.swing.JTextField txtNombreComercial;
    private javax.swing.JTextField txtPropietario;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtRazonSocial;
    // End of variables declaration//GEN-END:variables
}