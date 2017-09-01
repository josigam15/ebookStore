/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Josiane Gamgo
 * Created: 2016-02-21
 */

DROP DATABASE IF EXISTS ebooksstore;
-- Supprime la BD si elle existe
CREATE DATABASE `ebooksstore` CHARACTER SET utf8 COLLATE utf8_general_ci;
-- grant all access to the user manager
GRANT ALL PRIVILEGES ON EBOOKSSTORE.* TO Manager@'%' IDENTIFIED BY 'concordia';
GRANT ALL PRIVILEGES ON EBOOKSSTORE.* TO Manager@'localhost' IDENTIFIED BY 'concordia';

--grant select access to the user consumer
GRANT SELECT ON EBOOKSSTORE.* TO Consumer@'%' IDENTIFIED BY 'concordia';
GRANT SELECT ON EBOOKSSTORE.* TO Consumer@'localhost' IDENTIFIED BY 'concordia';
