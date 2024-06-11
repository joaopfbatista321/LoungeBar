const express = require('express');
const cors = require('cors')
const bodyParser = require('body-parser');
const pool  = require('./db');
const app = express();




// Middleware
// configure express to use json as middle-ware
app.use(cors());
app.use(express.json({limit: '200mb'}));

// Rota de registro
app.post('/register', async (req, res) => {
  
  const { username, password, role } = req.body;
  const sql = 'INSERT INTO users (username, password, role) VALUES ($1, $2, $3)';
  try {
    const result = await pool.query(sql, [username, password, role]);
    res.send('Usuário registrado com sucesso');
  } catch (err) {
    console.error('Erro ao registrar usuário:', err);
    res.status(500).send('Erro ao registrar usuário');
  }
});

// Rota de login
app.post('/login', async (req, res) => {
  const { username, password } = req.body;
  const sql = 'SELECT * FROM users WHERE username = $1 AND password = $2';
  try {
    const results = await pool.query(sql, [username, password]);
    if (results.rows.length > 0) {
      res.send('Login bem-sucedido');
    } else {
      res.status(401).send('Credenciais inválidas');
    }
  } catch (err) {
    console.error('Erro ao realizar login:', err);
    res.status(500).send('Erro ao realizar login');
  }
});
const PORT = process.env.PORT || 5000
app.listen(PORT, () => {
  console.log(`Servidor rodando em ${PORT}`);
});
