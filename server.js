const express = require('express');
const bodyParser = require('body-parser');
const mysql = require('mysql2');
const app = express();
const port = 3000;

// Conexão com o banco de dados MariaDB
const db = mysql.createConnection({
  host: '127.0.0.1',
  user: 'root', // substitua pelo usuário do seu banco de dados
  password: 'root', // substitua pela senha do seu banco de dados
  database: 'Lounge'
});

// Conectar ao banco de dados
db.connect((err) => {
  if (err) {
    console.error('Erro ao conectar ao MariaDB:', err);
    return;
  }
  console.log('Conectado ao MariaDB');
});

// Middleware
app.use(bodyParser.json());

// Rota de registro
app.post('/register', (req, res) => {
  const { username, password, role } = req.body;
  const sql = 'INSERT INTO users (username, password, role) VALUES (?, ?, ?)';
  db.query(sql, [username, password, role], (err, result) => {
    if (err) {
      console.error('Erro ao registrar usuário:', err);
      res.status(500).send('Erro ao registrar usuário');
      return;
    }
    res.send('Usuário registrado com sucesso');
  });
});

// Rota de login
app.post('/login', (req, res) => {
  const { username, password } = req.body;
  const sql = 'SELECT * FROM users WHERE username = ? AND password = ?';
  db.query(sql, [username, password], (err, results) => {
    if (err) {
      console.error('Erro ao realizar login:', err);
      res.status(500).send('Erro ao realizar login');
      return;
    }
    if (results.length > 0) {
      res.send('Login bem-sucedido');
    } else {
      res.status(401).send('Credenciais inválidas');
    }
  });
});

app.listen(port, () => {
  console.log(`Servidor rodando em http://localhost:${port}`);
});
