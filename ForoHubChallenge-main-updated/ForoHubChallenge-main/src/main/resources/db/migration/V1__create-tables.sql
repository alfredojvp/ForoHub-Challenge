-- Tabla: curso
CREATE TABLE cursos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

-- Tabla: usuario
CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) UNIQUE NOT NULL,
    clave VARCHAR(255) NOT NULL
);

-- Tabla: perfil
CREATE TABLE perfiles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL
);

-- Tabla intermedia: usuario_perfil (relación muchos a muchos entre usuario y perfil)
CREATE TABLE usuario_perfil (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE,
    FOREIGN KEY (perfil_id) REFERENCES perfiles (id) ON DELETE CASCADE
);

-- Tabla: temas
CREATE TABLE temas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    respuestas BIGINT DEFAULT 0,
    FOREIGN KEY (autor) REFERENCES usuarios (id) ON DELETE CASCADE,
    FOREIGN KEY (curso) REFERENCES cursos (id) ON DELETE CASCADE
);

-- Tabla: respuesta
CREATE TABLE respuestas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    tema BIGINT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    autor BIGINT NOT NULL,
    solucion BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (tema) REFERENCES temas (id) ON DELETE CASCADE,
    FOREIGN KEY (autor) REFERENCES usuarios (id) ON DELETE CASCADE
);
