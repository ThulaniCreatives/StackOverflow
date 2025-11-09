package com.thulanicreatives.stackoverflow.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thulanicreatives.stackoverflow.R
import com.thulanicreatives.stackoverflow.domain.model.Answer
import com.thulanicreatives.stackoverflow.util.convertUnixToDateTime


@Composable
fun QuestionListSection(answer: Answer, index: Int, size: Int, modifier: Modifier) {

    val typography = MaterialTheme.typography

    val createdDate = convertUnixToDateTime(answer.creationDate.toLong())
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(R.drawable.ic_check),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(30.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, top = 8.dp, bottom = 0.dp, end = 8.dp)
        ) {
            Text(
                text = "Q: ${answer.title}",
                style = typography.titleSmall,
                letterSpacing = 0.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = colorResource(R.color.primary_blue)
            )
            Text(
                AnnotatedString.fromHtml(
                    answer.body
                ),
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                letterSpacing = 0.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black.copy(0.75f),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )

            Row {
                Text(
                    text = "asked $createdDate by ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = answer.owner.display_name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.primary_blue),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        Column(modifier = Modifier.padding(4.dp)) {
            Text(
                text = "${answer.answerCount} answers",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "${answer.score} votes",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "${answer.viewCount} views",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Image(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp)
        )

    }

    if (index < size) {
        Divider(modifier = Modifier.padding(horizontal = 16.dp))
    }
}
