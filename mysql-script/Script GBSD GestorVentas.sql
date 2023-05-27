CREATE SCHEMA `gestor` ;
CREATE TABLE `gestor`.`vendedor` (
  `idVendedor` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellido` VARCHAR(45) NOT NULL,
  `Telefono`  VARCHAR(10) NOT NULL,
  `Correo` VARCHAR(45) NOT NULL,
  `Ventas` INT NULL,
  `Precio_Ventas` INT NULL,
  PRIMARY KEY (`idVendedor`));
CREATE TABLE `gestor`.`cliente` (
  `idCliente` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellido` VARCHAR(45) NOT NULL,
  `Telefono`  VARCHAR(10) NOT NULL,
  `Direccion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCliente`));
CREATE TABLE `gestor`.`producto` (
  `idProducto` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(45) NOT NULL,
  `Cantidad_actual` INT NOT NULL,
  `Precio` INT NOT NULL,
  PRIMARY KEY (`idProducto`));
  CREATE TABLE `gestor`.`venta` (
  `idVenta` INT NOT NULL,
  `vendedor_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  `forma_pago` INT NOT NULL,
  `fecha` DATETIME NOT NULL,
  `nombre_vendedor` VARCHAR(45) NOT NULL,
  `nombre_cliente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idVenta`),
  INDEX `cliente_id_idx` (`cliente_id` ASC) VISIBLE,
  INDEX `vendedor_id_idx` (`vendedor_id` ASC) VISIBLE,
  CONSTRAINT `cliente_id`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `gestor`.`cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `vendedor_id`
    FOREIGN KEY (`vendedor_id`)
    REFERENCES `gestor`.`vendedor` (`idVendedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    CREATE TABLE `gestor`.`detalleventa` (
  `idDetalleVenta` INT NOT NULL,
  `producto_codigo` INT NOT NULL,
  `venta_id` INT NOT NULL,
  `cantidad_vendida` INT NOT NULL,
  PRIMARY KEY (`idDetalleVenta`),
  INDEX `producto_codigo_idx` (`producto_codigo` ASC) VISIBLE,
  INDEX `venta_id_idx` (`venta_id` ASC) VISIBLE,
  CONSTRAINT `producto_codigo`
    FOREIGN KEY (`producto_codigo`)
    REFERENCES `gestor`.`producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `venta_id`
    FOREIGN KEY (`venta_id`)
    REFERENCES `gestor`.`venta` (`idVenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
   

INSERT INTO `gestor`.`vendedor` (`idVendedor`, `Nombre`, `Apellido`, `Telefono`, `Correo`) VALUES ('1', 'Cristhian', 'Morales', '3136104324', 'est.cristhian.mora@unimilitar.edu.co');
INSERT INTO `gestor`.`vendedor` (`idVendedor`, `Nombre`, `Apellido`, `Telefono`, `Correo`) VALUES ('2', 'Edgar', 'Castellanos', '3105562354', 'est.edgar.castellanos@unimilitar.edu.co');
INSERT INTO `gestor`.`producto` (`idProducto`, `Nombre`, `Descripcion`, `Cantidad_actual`, `Precio`) VALUES ('1', 'Zapatos', 'Calzado', '5', '79500');
INSERT INTO `gestor`.`producto` (`idProducto`, `Nombre`, `Descripcion`, `Cantidad_actual`, `Precio`) VALUES ('2', 'Camisa', 'Ropa Suave para Hombre o Mujer', '6', '67000');
INSERT INTO `gestor`.`producto` (`idProducto`, `Nombre`, `Descripcion`, `Cantidad_actual`, `Precio`) VALUES ('3', 'Pantalon', 'Jeans de Velcro', '3', '75600');
INSERT INTO `gestor`.`producto` (`idProducto`, `Nombre`, `Descripcion`, `Cantidad_actual`, `Precio`) VALUES ('4', 'Gorra', 'Protegete del sol', '2', '20000');

INSERT INTO `gestor`.`cliente` (`idCliente`, `Nombre`, `Apellido`, `Telefono`, `Direccion`) VALUES ('1', 'Mauricio', 'Barreto', '3027254515', 'Carrera 17 #8 A');
INSERT INTO `gestor`.`cliente` (`idCliente`, `Nombre`, `Apellido`, `Telefono`, `Direccion`) VALUES ('2', 'Laura', 'Pineda', '3136108431', 'Calle 100 #8 A');