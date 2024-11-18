const URL_BASE = '/api.agenda'
const express = require('express')
const app = express()
const morgan = require('morgan')
app.use(morgan('dev'))
const bodyParser = require('body-parser')
app.use(bodyParser.urlencoded({extended : false}))
app.use(bodyParser.json())

/* app.use(URL_BASE, (req, res, next) => {
    res.status(200).send({
        resposta : 'URL do app funcionou!'
    })
})

app.use((req, res, next) => {
    const erro = new Error('Rota não encontrada')
    erro.status = 404;
    next(erro)
}) */

const contatoDao = require('./rotas/contatoDao')
app.use(URL_BASE + '/contato-dao', contatoDao)

module.exports = app