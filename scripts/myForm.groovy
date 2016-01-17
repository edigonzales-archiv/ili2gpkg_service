html.html {
    head {
        title 'Sample to Upload Files to Google App Engine'
        style '''
            label { display: block; padding: 0.2em; }
        '''
    }
    body {
        h1 'Sample to Upload Files to Google App Engine'
        form action: 'action.groovy', method: 'post', enctype: 'multipart/form-data', {
            label 'Username: ', {
                input type: 'text', name: 'username'
            }
            label 'Likes: ', {
                select name: 'language', {
                    option 'Groovy'
                    option 'Java'
                    option 'Scala'
                }
            }
            label 'Photo: ', {
                input type: 'file', name: 'photo'
            }
            label 'Resume: ', {
                input type: 'file', name: 'resume'
            }
            input type: 'submit', name: 'submit', value: 'Send to server'
        }
    }
}
