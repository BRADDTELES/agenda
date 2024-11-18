const express = require('express') // Biblioteca do Express
const router = express.Router() // Gerenciador de rotas
const url = require('url') // Ter acesso a url
const querystring = require('querystring') // Filtrar url
//const { error } = require('console')
const mysql = require('./mysql').pool // Importar configuração do MYSQL

// http://localhost:3000/api.agenda/contato-dao/create
/**
 {
    "nome" : "Jose",
    "fone" : "(99)99999-9999",
    "email" : "jose@gmail.com"
 }*/
router.post('/create', (req, res, next) => {
    const { nome, fone, email } = req.body
    const contato = { nome, fone, email }
/* 
    res.status(201).send({
        mensagem: 'Contato criado!',
        "Dados contato: ": contato
    })
     */
    mysql.getConnection((error, conn) => {
        if (error) {
            return res.status(500).send({
                error: error,
                response: null
            })
        }
        conn.query(
            'INSERT INTO contato (nome , fone , email) VALUES (? , ? , ?)',
            [contato.nome, contato.fone, contato.email],
            (error, resultado, field) => {
                conn.release();
                if (error) {
                    return res.status(500).send({
                        error: error,
                        response: null
                    });
                }
                res.status(201).send({
                    response: 'Contato cadastrado com sucesso!',
                    id_contato: resultado.insertId
                });
            }
        )
    });
});

// http://localhost:3000/api.agenda/contato-dao/getId?id=1
router.get('/getId', (req, res, next) => {
    const reqUrl = url.parse(req.url)
    const queryParams = querystring.parse(reqUrl.query)
    const param = queryParams.id
    /*
    res.status(200).send({
        mensagem: 'Contato encontrado! >> ' + param
    });
    */
    mysql.getConnection((error, conn) => {
        if (error) {
            return res.status(500).send({
                error: error,
                response: null
            });
        }
        conn.query(
            'SELECT * FROM contato WHERE id = ?; ',
            [param],
            (error, resultado, field) => {
                conn.release();
                if (error) {
                    return res.status(500).send({
                        error: error,
                        response: null
                    });
                }
                return res.status(201).send({
                    response: resultado
                });
            }
        )
    });
});

// http://localhost:3000/api.agenda/contato-dao/getAll
router.get('/getAll', (req, res, next) => {
    /*
    res.status(200).send({
        mensagem: 'Contatos encontrados!'
    })
    */
    mysql.getConnection((error, conn) => {
        if (error) {
            return res.status(500).send({
                error: error,
                response: null
            });
        }
        conn.query(
            'SELECT * FROM contato',
            (error, resultado, field) => {
                conn.release();
                if (error) {
                    return res.status(500).send({
                        error: error,
                        response: null
                    });
                }
                return res.status(201).send({
                    response: resultado
                });
            }
        )
    });
})

/**
{
    "id": 1,
    "nome": "Saul Terror",
    "fone": "629123-0000",
    "email": "saul-terrorista@gmail.com"
}
 */

// http://localhost:3000/api.agenda/contato-dao/update
router.post('/update', (req, res, next) => {
    const { id_contato, nome, fone, email } = req.body
    const contato = { id_contato, nome, fone, email }
    /*
    res.status(201).send({
        mensagem: 'Contato atualizado!',
        "Dados contato: ": contato
    })
    */
    mysql.getConnection((error, conn) => {
        if (error) {
            return res.status(500).send({
                error: error,
                response: null
            });
        }
        conn.query(
            'UPDATE contato SET nome = ? , fone = ? , email = ? WHERE id = ? ;',
            [contato.nome, contato.fone, contato.email, contato.id_contato],
            (error, resultado, field) => {
                conn.release();
                if (error) {
                    return res.status(500).send({
                        error: error,
                        response: null
                    });
                }
                return res.status(200).send({
                    response: 'Contato atualizado com sucesso!',
                    'Dados do contato' : contato
                });
            }
        )
    });
});

/**
 {
    "id_contato" : "1"
 }
 */
// http://localhost:3000/api.agenda/contato-dao/delete
router.post('/delete', (req, res, next) => {
    const { id_contato } = req.body
    const contato = { id_contato }
    mysql.getConnection((error, conn) => {
        if (error) {
            return res.status(500).send({
                error: error,
                response: null
            });
        }
        conn.query(
            'DELETE FROM contato WHERE id = ? ;',
            [contato.id_contato],
            (error, resultado, field) => {
                conn.release();
                if (error) {
                    return res.status(500).send({
                        error: error,
                        response: null
                    });
                }
                return res.status(201).send({
                    response: 'Contato excluído com sucesso!',
                    'ID do contato excluido' : contato.id_contato
                });
            }
        )
    });
})

module.exports = router