/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import conexion.ConnectionTableDB;
import ec.gob.sri.comprobantes.ws.Comprobante;
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;
import ec.gob.sri.comprobantes.ws.aut.Autorizacion;
import ec.gob.sri.comprobantes.ws.aut.Mensaje;
import facturacion.crear_clave_acceso;
import facturacion.crear_nota_credito_normal;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import seguridad.CertificadosSSL;
import seguridad.FirmaDigital;
import util.ArchivosUtil;
import ws.AutorizacionComprobantesWS;
import ws.EnvioComprobantesWS;

/**
 *
 * @author Advantech Digital
 */
public class CreditoFactura extends javax.swing.JFrame {

    private ConnectionTableDB model;
    private String USER = "";
    private String PASSWORD = "";
    private final String DB = "adv_facturacion";
    private InputStream PKCS12_RESOURCE;
    private String PKCS12_PASSWORD;
    private int idUser, idCliente = 0, idFactura, idClave;
    private int rowClientes = -1;
    private int rowXML = -1;
    private Date fechaIn, fechaFin;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private TableRowSorter<TableModel> sorter;
    public String ambiente, razonSocial, nombreComercial, ruc, sec2, sec1, secuencial, direccion;
    public String fechaEmision, tipo, nCliente, idC, contribuyente, contabilidad, factura, fechaFactura;
    private String claveAcceso, Ewsri, Awsri;
    private String prod, pExtra, pSuper, pDiesel;
    private double cantidadT, ppuT, subtotalT, ivaT, totalT;
    private double cantidadE = 0.000, ppuE = 0.000, subtotalE = 0.00, ivaE = 0.000, totalE = 0.00;
    private double cantidadS = 0.000, ppuS = 0.000, subtotalS = 0.00, ivaS = 0.000, totalS = 0.00;
    private double cantidadD = 0.000, ppuD = 0.000, subtotalD = 0.00, ivaD = 0.000, totalD = 0.00;
    private String[] productos;
    private double[] cantidades, ppus, subtotales, ivas, totales;
    private int notaCredito;

    /**
     * Creates new form CreditoFactura
     */
    public CreditoFactura(String user, String password) {
        USER = user;
        PASSWORD = password;
        try {
            String consulta = "SELECT tipo_ambiente, razon_social, nombre_comercial, ruc, secuencia2_factura, secuencia1_factura, direccion, "
                    + "email_estacion, contribuyente_especial, obligado_llevar_contabilidad "
                    + "FROM datos_gasolinera";
            model = new ConnectionTableDB(USER, PASSWORD, DB, consulta, true);
            ambiente = (String) model.getValueAt(0, 0);
            razonSocial = (String) model.getValueAt(0, 1);
            nombreComercial = (String) model.getValueAt(0, 2);
            ruc = (String) model.getValueAt(0, 3);
            sec2 = (String) model.getValueAt(0, 4);
            sec1 = (String) model.getValueAt(0, 5);
            direccion = (String) model.getValueAt(0, 6);
            fechaEmision = String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance());
            //direccionE = (String)model.getValueAt(0, 6);
            contribuyente = (String) model.getValueAt(0, 8);
            contabilidad = (String) model.getValueAt(0, 9);
            if (ambiente.equalsIgnoreCase("1")) {
                Ewsri = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl";
                Awsri = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";
            } else {
                Ewsri = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl";
                Awsri = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";
            }
            java.sql.ResultSet rs = model.stSentencias.executeQuery("SELECT certificado_digital, AES_DECRYPT(contraseña_certificado,'thekey') FROM datos_gasolinera");
            rs.next();
            PKCS12_RESOURCE = rs.getBlob(1).getBinaryStream();
            PKCS12_PASSWORD = rs.getString(2);
            model.consulta("SELECT cedula_ruc, nombre, numero FROM cliente, factura WHERE credito_cliente = 1 "
                    + "AND idCliente = cliente_idcliente AND fecha <= '" + String.format("%tF", Calendar.getInstance()) + "' "
                    + "AND Estado_factura = 'AUTORIZADO' AND idcliente = 0");

            initComponents();
            setSize(1200, 620);
            fechaInicial.setText(String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()));
            fechaFinal.setText(String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()));
            if (tablaFacturas.getRowCount() == 0) {
                txtAFacturas.setText("No existen facturas de crédito para el\ncliente y periodo seleccionados.");
            }
            Clientes.setUndecorated(true);
            Clientes.setSize(1200, 560);
            sorter = new TableRowSorter<TableModel>(model);
            sorter.setRowFilter(null);
            tablaClientes.setRowSorter(sorter);
        } catch (SQLException | IllegalStateException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /*private void createTable(){
        
     }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Clientes = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtBuscado = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnBuscarCliente = new javax.swing.JButton();
        btnFacturas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fechaInicial = new datechooser.beans.DateChooserCombo();
        fechaFinal = new datechooser.beans.DateChooserCombo();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFacturas = new javax.swing.JTable(model);
        jPanel4 = new javax.swing.JPanel();
        btnNotaCredito = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAFacturas = new javax.swing.JTextArea();

        Clientes.setExtendedState(2);
        Clientes.setName("frameClientes"); // NOI18N
        Clientes.setResizable(false);

        jLabel3.setText("Buscar:");
        jPanel2.add(jLabel3);

        txtBuscar.setColumns(30);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });
        jPanel2.add(txtBuscar);

        Clientes.getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        tablaClientes.setModel(model);
        tablaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaClientesMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tablaClientes);

        Clientes.getContentPane().add(jScrollPane3, java.awt.BorderLayout.CENTER);

        txtBuscado.setEditable(false);
        txtBuscado.setColumns(30);
        jPanel3.add(txtBuscado);

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });
        jPanel3.add(btnOK);

        Clientes.getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        Clientes.getAccessibleContext().setAccessibleParent(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Asignar Cliente");
        setName("interfaz"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btnBuscarCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscarCliente.setText("Buscar Cliente de Crédito");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnFacturas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFacturas.setText("Buscar Facturas");
        btnFacturas.setEnabled(false);
        btnFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Id:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Cliente:");

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtCliente.setEditable(false);
        txtCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Fecha Inicial:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Fecha Final:");

        fechaInicial.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    fechaInicial.setNothingAllowed(false);
    fechaInicial.setFormat(2);
    fechaInicial.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

    fechaFinal.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
fechaFinal.setNothingAllowed(false);
fechaFinal.setFormat(2);
fechaFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addComponent(jLabel2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addComponent(txtId)
            .addComponent(btnBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
            .addComponent(txtCliente))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 565, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
            .addComponent(btnFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fechaFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(fechaInicial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(38, 38, 38))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(btnBuscarCliente)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)))
                .addComponent(fechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addComponent(fechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

    jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane1.setPreferredSize(new java.awt.Dimension(600, 400));

    tablaFacturas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    tablaFacturas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tablaFacturas.setShowVerticalLines(false);
    tablaFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            tablaFacturasMousePressed(evt);
        }
    });
    jScrollPane1.setViewportView(tablaFacturas);

    getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

    btnNotaCredito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    btnNotaCredito.setText("Crear Nota de Crédito");
    btnNotaCredito.setEnabled(false);
    btnNotaCredito.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnNotaCreditoActionPerformed(evt);
        }
    });
    jPanel4.add(btnNotaCredito);

    getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

    jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane2.setPreferredSize(new java.awt.Dimension(400, 400));

    txtAFacturas.setEditable(false);
    txtAFacturas.setColumns(10);
    txtAFacturas.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
    txtAFacturas.setRows(5);
    txtAFacturas.setTabSize(4);
    txtAFacturas.setPreferredSize(new java.awt.Dimension(400, 400));
    jScrollPane2.setViewportView(txtAFacturas);

    getContentPane().add(jScrollPane2, java.awt.BorderLayout.WEST);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showFact(String idFactura, boolean consulta) throws SQLException, IllegalStateException {
        model.consulta("SELECT cupo_cliente, producto.nombre, cantidad, punit, subtotal, iva, total, factura.usuarios_idusuarios "
                + "FROM cliente, producto, factura_detalle, factura, configuracion "
                + "WHERE idfactura = factura_idfactura AND factura.cliente_idcliente = idcliente "
                + "AND configuracion_nmanguera = nmanguera AND idproducto = producto_idproducto "
                + "AND idcliente = " + idCliente + " AND numero = '" + idFactura + "'");
        String credito = String.format("%.2f", (BigDecimal) model.getValueAt(0, 0));
        String producto = String.format("%s", (String) model.getValueAt(0, 1)).toUpperCase();
        String cantidad = String.format("%.3f", (BigDecimal) model.getValueAt(0, 2));
        String precioU = String.format("%.3f", (BigDecimal) model.getValueAt(0, 3));
        String subtotal = String.format("%.2f", (BigDecimal) model.getValueAt(0, 4));
        String iva = String.format("%.3f", (BigDecimal) model.getValueAt(0, 5));
        String total = String.format("%.2f", (BigDecimal) model.getValueAt(0, 6));
        this.idFactura = Integer.parseInt(idFactura.substring(8));
        idUser = (Integer) model.getValueAt(0, 7);

        if (!consulta) {
            ppuT = ((BigDecimal) model.getValueAt(0, 3)).doubleValue();
            cantidadT = ((BigDecimal) model.getValueAt(0, 2)).doubleValue();
            subtotalT = ((BigDecimal) model.getValueAt(0, 4)).doubleValue();
            ivaT = ((BigDecimal) model.getValueAt(0, 5)).doubleValue();
            totalT = ((BigDecimal) model.getValueAt(0, 6)).doubleValue();

            if (producto.equalsIgnoreCase("EXTRA")) {
                prod = "1," + producto;
                ppuE = ppuT;
                cantidadE += cantidadT;
                subtotalE += subtotalT;
                ivaE += ivaT;
                totalE += totalT;
            } else if (producto.equalsIgnoreCase("SUPER")) {
                prod = "2," + producto;
                ppuS = ppuT;
                cantidadS += cantidadT;
                subtotalS += subtotalT;
                ivaS += ivaT;
                totalS += totalT;
            } else if (producto.equalsIgnoreCase("DIESEL")) {
                prod = "3," + producto;
                ppuD = ppuT;
                cantidadD += cantidadT;
                subtotalD += subtotalT;
                ivaD += ivaT;
                totalD += totalT;
            }
        }

        model.consulta("SELECT cedula_ruc, nombre, numero, fecha FROM cliente, factura WHERE credito_cliente = 1 "
                + "AND idCliente = cliente_idcliente "
                + "AND fecha >= '" + String.format("%tF", fechaIn) + "' "
                + "AND fecha <= '" + String.format("%tF", fechaFin) + "' "
                + "AND Estado_factura = 'AUTORIZADO' AND idCliente = " + idCliente);

        txtAFacturas.setText(String.format("Factura Seleccionada: %s\n"
                + "Fecha:                %s\n\n"
                + "EMISOR:\n"
                + "%-15s%20s\n"
                + "%-15s%20s\n"
                + "%-15s%20s\n\n"
                + "CLIENTE:\n"
                + "%-15s%20s\n"
                + "%-15s%20s\n"
                + "%-15s%20s\n\n"
                + "DETALLE:\n"
                + "%-15s%20s\n"
                + "%-15s%20s galones.\n"
                + "%-15s%20s\n"
                + "%-15s%19s\n"
                + "%-15s%20s\n"
                + "%-15s%19s\n",
                (String) tablaFacturas.getValueAt(rowXML, 2),
                String.format("%tF", tablaFacturas.getValueAt(rowXML, 3)),
                "Nombre: ", razonSocial,
                "Comercial: ", nombreComercial,
                "Id. Emisor: ", ruc,
                "Nombre: ", txtCliente.getText(),
                "Id: ", txtId.getText(),
                "CUPO CREDITO: ", credito,
                "Producto: ", producto,
                "Cantidad: ", cantidad,
                "PPU: ", "$ " + precioU,
                "Subtotal: ", "$ " + subtotal,
                "IVA: ", "$ " + iva,
                "Total Factura: ", "$ " + total));
    }

    private void prepareInfo(int index) throws SQLException, IllegalStateException {
        nCliente = txtCliente.getText();
        idC = txtId.getText();
        factura = (String) tablaFacturas.getValueAt(index, 2);
        fechaFactura = String.format("%1$td/%1$tm/%1$tY", tablaFacturas.getValueAt(index, 3));
        showFact(factura, false);
        model.consulta("SELECT idclave_acceso, clave_acceso FROM clave_acceso WHERE estado = 0 AND tipo = 'contingencia'");
        idClave = (Integer) model.getValueAt(0, 0);
        claveAcceso = (String) model.getValueAt(0, 1);
        model.ejecutar("UPDATE clave_acceso SET estado = 1 WHERE idclave_acceso = " + idClave);
    }

    private void crearNormal(String num) throws MalformedURLException, JAXBException, IOException {
        try {
            java.sql.ResultSet rs = model.stSentencias.executeQuery("SELECT certificado_digital FROM datos_gasolinera");
            rs.next();
            PKCS12_RESOURCE = rs.getBlob(1).getBinaryStream();
            model.ejecutar("UPDATE clave_acceso SET estado = 0 WHERE idclave_acceso = " + idClave + "");
            String clave = new crear_clave_acceso().crear_clave_acceso(fechaEmision.replace("/", ""), "04", ruc, ambiente,
                    sec1 + sec2, num, "12345678", "1");
            notaCredito = new crear_nota_credito_normal().crear_nota_credito_normal(prod.substring(0, 1), nombreComercial, contabilidad, Integer.parseInt(contribuyente), ambiente,
                    razonSocial, ruc, clave, sec1, sec2, num, direccion, fechaEmision, tipo, nCliente, idC,
                    subtotalT, ivaT, totalT, prod.substring(2), cantidadT, ppuT, fechaFactura, factura, "ANULACION");

            if (notaCredito == 1) {
                model.ejecutar("INSERT INTO clave_acceso (clave_acceso, fecha, estado, tipo) VALUES ('" + clave + "', CURDATE(), 1, 'normal')");

                FirmaDigital fd = new FirmaDigital("notas_generadas\\nc" + sec1 + "-" + sec2 + "-" + num + ".xml", "notas_firmadas\\nota_firmada" + sec1 + "-" + sec2 + "-" + num + ".xml");
                fd.setPKCS12_RESOURCE(PKCS12_RESOURCE);
                fd.setPKCS12_PASSWORD(PKCS12_PASSWORD);
                fd.firmarDocumentoXML();

                //File xsd = new File("factura_v1.0.0.xsd");
                File temp = new File("notas_firmadas\\nota_firmada" + sec1 + "-" + sec2 + "-" + num + ".xml");

                CertificadosSSL.instalarCertificados();
                RespuestaSolicitud response;
                try {
                    EnvioComprobantesWS ws = new EnvioComprobantesWS(Ewsri);
                    response = ws.enviarComprobante(temp);
                } catch (javax.xml.ws.WebServiceException ex) {
                    EnvioComprobantesWS ws = new EnvioComprobantesWS(Ewsri);
                    response = ws.enviarComprobante(temp);
                }

                //RespuestaSolicitud response = ws.enviarComprobante(new File("D:\\ejemplos\\Firmados\\pruebaguia.xml"));
                System.out.println(response.getEstado());

                System.out.println("---");
                RespuestaSolicitud.Comprobantes comprobantes = response.getComprobantes();
                if (comprobantes != null) {
                    List<Comprobante> listaComprobantes = comprobantes.getComprobante();
                    if (!listaComprobantes.isEmpty()) {
                        for (Comprobante comprobante : listaComprobantes) {
                            List<ec.gob.sri.comprobantes.ws.Mensaje> mensajes = comprobante.getMensajes().getMensaje();
                            for (ec.gob.sri.comprobantes.ws.Mensaje mensaje : mensajes) {
                                System.out.println(mensaje.getIdentificador() + "\t" + mensaje.getMensaje() + "\t" + mensaje.getInformacionAdicional());
                            }
                        }
                    }
                }

                System.out.println("autorizando nota de credito " + num);

                if (response.getEstado().equalsIgnoreCase("RECIBIDA")) {
                    List<Autorizacion> listaAutorizaciones;
                    ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante respuesta;
                    int c = 0;
                    try {
                        respuesta = (new AutorizacionComprobantesWS(Awsri)).llamadaWSAutorizacionInd(clave);
                        listaAutorizaciones = respuesta.getAutorizaciones().getAutorizacion();

                        while (listaAutorizaciones.isEmpty()) {
                            respuesta = (new AutorizacionComprobantesWS(Awsri)).llamadaWSAutorizacionInd(clave);
                            listaAutorizaciones = respuesta.getAutorizaciones().getAutorizacion();
                            System.out.println();
                            c++;
                        }
                    } catch (java.lang.NullPointerException ex) {
                        respuesta = (new AutorizacionComprobantesWS(Awsri)).llamadaWSAutorizacionInd(clave);
                        listaAutorizaciones = respuesta.getAutorizaciones().getAutorizacion();
                        while (listaAutorizaciones.isEmpty()) {
                            respuesta = (new AutorizacionComprobantesWS(Awsri)).llamadaWSAutorizacionInd(clave);
                            listaAutorizaciones = respuesta.getAutorizaciones().getAutorizacion();
                            System.out.println();
                            c++;
                        }
                    }

                    System.out.println("Intentos " + c);
                    System.out.println(listaAutorizaciones.size());

                    for (Autorizacion autorizacion : listaAutorizaciones) {
                        System.out.println("entro a autorizacion factura " + num);
                        //Lógica de acceso a datos
                        String nAuto = autorizacion.getNumeroAutorizacion();
                        XMLGregorianCalendar fAuto = autorizacion.getFechaAutorizacion();
                        //--
                        String estado = autorizacion.getEstado().toUpperCase();
                        String ta;
                        FileInputStream in;

                        if (estado.compareTo("AUTORIZADO") == 0) {
                            ArchivosUtil.guardarDocumentoXMLAutorizado(autorizacion, "notas_autorizadas\\nota_autorizada" + sec1 + "-" + sec2 + "-" + num + ".xml");

                            ta = (ambiente.equalsIgnoreCase("1") ? "Pruebas" : "Produccion");

                            /*reporte_factura rf = new reporte_factura();
                             rf.reporte_factura(s1 + "-" + s2 + "-" + ss3, r, d, d, ocont, String.valueOf(cespecial), nAuto, String.valueOf(fAuto), cadena, "Normal", ta, rz, nc, ncliente, clientr, f.getText(), "", String.valueOf(subtotal), String.valueOf(iva), String.valueOf(total), "0", codigop, producto, String.valueOf(cantidad), producto, String.valueOf(pu), "0");
                             rf.enviar_email(contramail,maile, "autorizados\\autorizado" + s1 + "-" + s2 + "-" + ss3 + ".xml", "pdf\\" + s1 + "-" + s2 + "-" + ss3 + ".pdf", email, ncliente, rz, s1 + "-" + s2 + "-" + ss3 + ".pdf", s1 + "-" + s2 + "-" + ss3 + ".xml");
                             rf.run();*/

                            temp = new File("notas_autorizadas\\nota_autorizada" + sec1 + "-" + sec2 + "-" + num + ".xml");
                            model.consulta("SELECT idclave_acceso FROM clave_acceso WHERE clave_acceso = '" + clave + "'");
                            model.ejecutar("INSERT INTO nota_credito (factura_idfactura, factura_cliente_idcliente, factura_usuarios_idusuarios, clave_acceso_idclave_acceso, valor_modificacion, motivo, estado) "
                                    + "VALUES (" + idFactura + ", " + idCliente + ", " + idUser + ", " + model.getValueAt(0, 0) + ", " + totalT + ", 'ANULACION', 'AUTORIZADO')");
                            model.psPrepararSentencias = model.coneccion.prepareStatement("INSERT INTO adv_xml.xml_enviados_autorizados (doc_xml, xml_factura) "
                                    + "VALUES(?, '" + idFactura + "')");
                            in = new FileInputStream(temp);
                            model.psPrepararSentencias.setBinaryStream(1, in, (int) temp.length());
                            model.psPrepararSentencias.executeUpdate();
                            model.psPrepararSentencias.close();
                        } else {
                            int con = 0;
                            String mensajeE = null;
                            List<Mensaje> mensajes = autorizacion.getMensajes().getMensaje();
                            for (Mensaje mensaje : mensajes) {
                                ArchivosUtil.guardarDocumentoXMLAutorizado(autorizacion, "notas_no_autorizados\\" + sec1 + "-" + sec2 + "-" + num + ".xml");
                                temp = new File("notas_no_autorizados\\" + sec1 + "-" + sec2 + "-" + num + ".xml");
                                con++;
                                if (con == 1) {
                                    mensajeE = mensaje.getMensaje();
                                }
                            }
                            in = new FileInputStream(temp);
                            model.ejecutar("INSERT INTO nota_credito (factura_idfactura, factura_cliente_idcliente, factura_usuarios_idusuarios, clave_acceso_idclave_acceso, valor_modificacion, motivo, estado) "
                                    + "VALUES (" + idFactura + ", " + idCliente + ", " + idUser + ", " + Integer.parseInt(num) + ", " + totalT + ", 'ANULACION', 'estado')");
                            model.ejecutar("UPDATE factura SET Estado_factura = 'ANULADO' "
                                    + "WHERE idFactura = " + idFactura);
                            model.psPrepararSentencias = model.coneccion.prepareStatement("INSERT INTO adv_xml.xml_no_autorizados (doc_xml, xml_factura, motivo_no_autorizado) "
                                    + "VALUES(?, '" + idFactura + "','" + mensajeE + "')");
                            model.psPrepararSentencias.setBinaryStream(1, in, (int) temp.length());
                            model.psPrepararSentencias.executeUpdate();
                            model.psPrepararSentencias.close();
                        }
                        in.close();
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /*private void crearContingencia(String num) {
     try {
     claveAcceso = claveAcceso.substring(14);
     String ss1 = claveAcceso.substring(0, 5);
     String ss2 = claveAcceso.substring(5, 14);
     String ss3 = claveAcceso.substring(14);
            
            
     String clave = new crear_clave_acceso().crear_clave_acceso(fechaEmision.replace("/", ""), "04", ruc, ambiente, 
     ss1, ss2, ss3, "2");
     model.ejecutar("UPDATE clave_acceso SET clave_acceso = '" + clave + "' WHERE idclave_acceso = " + idClave + "");
     notaCredito = new crear_nota_credito_contingencia().crear_nota_credito_contingencia(prod.substring(0, 1), nombreComercial, contabilidad, Integer.parseInt(contribuyente), ambiente,
     razonSocial, ruc, clave, sec1, sec2, num, direccion, fechaEmision, tipo, nCliente, idC,
     subtotalT, ivaT, totalT, prod.substring(2), cantidadT, ppuT, fechaFactura, factura, "ANULACION");

     if (notaCredito == 1) {
     FirmaDigital fd = new FirmaDigital("notas_contingencia\\" + sec1 + "-" + sec2 + "-" + num + ".xml", "notas_firmadas\\nota_firmada" + sec1 + "-" + sec2 + "-" + num + ".xml");
     fd.setPKCS12_RESOURCE(PKCS12_RESOURCE);
     fd.setPKCS12_PASSWORD(PKCS12_PASSWORD);
     fd.firmarDocumentoXML();
     //File xsd = new File("factura_v1.0.0.xsd");
     File temp = new File("firmados\\firmado" + s1 + "-" + s2 + "-" + ss3 + ".xml");
     FileInputStream in = new FileInputStream(temp);
     model.ejecutar("INSERT INTO nota_credito (factura_idfactura, factura_cliente_idcliente, factura_usuarios_idusuarios, clave_acceso_idclave_acceso, valor_modificacion, motivo, estado) "
     + "VALUES (" + idFactura + ", " + idCliente + ", " + idUser + ", " + Integer.parseInt(num) + ", " + totalT + ", 'ANULACION', 'estado')");
     model.psPrepararSentencias = model.coneccion.prepareStatement("INSERT INTO adv_xml.xml_contigencia (xml_contingencia, xml_factura, motivo) "
     + "VALUES(?, '" + idFactura + "','Indisponibilidad del Sistema')");
     model.psPrepararSentencias.setBinaryStream(1, in, (int) temp.length());
     model.psPrepararSentencias.executeUpdate();
     model.psPrepararSentencias.close();
     in.close();
     guardar_datos g = new guardar_datos(tp, idcl, 2, axml, "Contingencia", "indisponibilidad del sistema", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
     g.usuarios(usuario, contra);
     g.actualizar(cadena, s1 + "-" + s2 + "-" + ss3, ss3);
     }
     } catch (SQLException ex) {
     System.err.println(ex.getMessage());
     }
     }*/
    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:
        btnBuscarCliente.setEnabled(false);
        Clientes.setVisible(true);
        Clientes.requestFocus();
        setEnabled(false);
        try {
            model.consulta("SELECT DISTINCT idcliente, cedula_ruc, nombre, tipo_identificacion, direccion, telefono, "
                    + "(SELECT codigo FROM codigos WHERE idcliente = cliente_idcliente) AS codigo "
                    + "FROM cliente, codigos WHERE credito_cliente = 1 LIMIT 100");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if (model.connected) {
            model.desconectar();
        }
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        setEnabled(true);
        txtId.requestFocus();
        Clientes.setVisible(false);
        btnFacturas.setEnabled(true);
        btnBuscarCliente.setEnabled(true);
        try {
            if (!(rowClientes < 0)) {
                txtId.setText((String) tablaClientes.getValueAt(rowClientes, 1));
                txtCliente.setText((String) tablaClientes.getValueAt(rowClientes, 2));
                if (tablaClientes.getValueAt(rowClientes, 3) != null) {
                    switch ((String) tablaClientes.getValueAt(rowClientes, 3)) {
                        case "cedula":
                            tipo = "05";
                            break;
                        case "ruc":
                            tipo = "04";
                            break;
                        case "pasaporte":
                            tipo = "06";
                            break;
                        case "placa":
                            tipo = "09";
                            break;
                        default:
                            tipo = null;
                            break;
                    }
                }
                //System.out.println(tipo);
                idCliente = (Integer) tablaClientes.getValueAt(rowClientes, 0);
                model.consulta("SELECT cedula_ruc, nombre, numero, fecha FROM cliente, factura WHERE credito_cliente = 1 "
                        + "AND idCliente = cliente_idcliente AND fecha = '" + String.format("%tF", Calendar.getInstance()) + "' "
                        + "AND Estado_factura = 'AUTORIZADO' AND idCliente = " + idCliente);
            } else {
                JOptionPane.showMessageDialog(this, "No ha seleccionado ningún cliente.");
                model.consulta("SELECT cedula_ruc, nombre, numero, fecha FROM cliente, factura WHERE credito_cliente = 1 "
                        + "AND idCliente = cliente_idcliente AND fecha = '" + String.format("%tF", Calendar.getInstance()) + "' "
                        + "AND Estado_factura = 'AUTORIZADO' AND idCliente = 0");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void tablaClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMousePressed
        // TODO add your handling code here:
        rowClientes = tablaClientes.rowAtPoint(evt.getPoint());
        txtBuscado.setText(String.format("Seleccionado: %s", (String) tablaClientes.getValueAt(rowClientes, 2)));
    }//GEN-LAST:event_tablaClientesMousePressed

    private void tablaFacturasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFacturasMousePressed
        // TODO add your handling code here:
        rowXML = tablaFacturas.rowAtPoint(evt.getPoint());
        try {
            fechaIn = dateFormat.parse(fechaInicial.getText());
            fechaFin = dateFormat.parse(fechaFinal.getText());
        } catch (ParseException ex) {
            System.err.println(ex.getMessage());
        }
        if (!(rowXML < 0)) {
            try {
                btnFacturas.setEnabled(true);
                String idFact = (String) tablaFacturas.getValueAt(rowXML, 2);
                showFact(idFact, true);
                tablaFacturas.setRowSelectionInterval(rowXML, rowXML);
                btnNotaCredito.setEnabled(true);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_tablaFacturasMousePressed

    private void btnFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturasActionPerformed
        if (txtCliente.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado ningún cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                fechaIn = dateFormat.parse(fechaInicial.getText());
                fechaFin = dateFormat.parse(fechaFinal.getText());
            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
            try {
                model.consulta("SELECT cedula_ruc, nombre, numero, fecha FROM cliente, factura WHERE credito_cliente = 1 "
                        + "AND idCliente = cliente_idcliente "
                        + "AND fecha >= '" + String.format("%tF", fechaIn) + "' "
                        + "AND fecha <= '" + String.format("%tF", fechaFin) + "' "
                        + "AND Estado_factura = 'AUTORIZADO' AND idCliente = " + idCliente);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnFacturasActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        String filtro = txtBuscar.getText();
        if (filtro.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtro));
            } catch (PatternSyntaxException ex) {
                System.err.println(ex.getMessage());
            }

        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void btnNotaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotaCreditoActionPerformed
        // TODO add your handling code here:
        int[] selectedRows = tablaFacturas.getSelectedRows();
        if (tablaFacturas.getRowCount() > 0 && selectedRows.length > 0) {
            for (int i = 0; i < selectedRows.length; i++) {
                try {
                    prepareInfo(selectedRows[i]);
                    if (ArchivosUtil.existeConexion(Ewsri, Awsri,false)) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    model.consulta("SHOW TABLE STATUS FROM adv_facturacion LIKE 'nota_credito'");
                                    String num = String.format("%09d", ((java.math.BigInteger) model.getValueAt(0, 10)).intValue());
                                    crearNormal(num);
                                } catch (SQLException | IllegalStateException | JAXBException | IOException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                        }).start();
                    }
                    /*System.out.println(String.format(ambiente + ", 1, " + razonSocial + ", " + nombreComercial + ", "
                            + ruc + ", 04, " + sec2 + ", " + sec1 + ", " + factura.substring(8) + ", " + direccion + "\n"
                            + fechaEmision + ", " + direccion + ", " + tipo + ", " + nCliente + ", " + idC + ", "
                            + contribuyente + ", " + contabilidad + ", rise, 01, " + factura + ", " + fechaFactura + ", " + subtotalT + ", " + totalT + "\n"
                            + prod.substring(0, 1) + ", " + prod.substring(2) + ", "
                            + cantidadT + ", " + ppuT + ", " + subtotalT + ", " + ivaT + ", " + totalT));*/
                    model.consulta("SELECT cedula_ruc, nombre, numero, fecha FROM cliente, factura WHERE credito_cliente = 1 "
                            + "AND idCliente = cliente_idcliente "
                            + "AND fecha >= '" + String.format("%tF", fechaIn) + "' "
                            + "AND fecha <= '" + String.format("%tF", fechaFin) + "' "
                            + "AND Estado_factura = 'AUTORIZADO' AND idCliente = " + idCliente);
                    } catch (SQLException | IllegalStateException ex) {
                        System.err.println(ex.getMessage());
                    }
            }
        }
    }//GEN-LAST:event_btnNotaCreditoActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreditoFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreditoFactura("root", "manager").setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame Clientes;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnFacturas;
    private javax.swing.JButton btnNotaCredito;
    private javax.swing.JButton btnOK;
    private datechooser.beans.DateChooserCombo fechaFinal;
    private datechooser.beans.DateChooserCombo fechaInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaFacturas;
    private javax.swing.JTextArea txtAFacturas;
    private javax.swing.JTextField txtBuscado;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
