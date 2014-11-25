module.exports = {
	main: [
	  { post: 'http://127.0.0.1/address', json: {firstname: Math.floor(Math.random()*100000), lastname: Math.floor(Math.random()*100000), email: Math.floor(Math.random()*100001)} },
	  { get: 'http://127.0.0.1/address' },
	  { get: 'http://127.0.0.1/address' }
	]
};
